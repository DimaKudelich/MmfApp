package com.kudelich.mmfapp.data.repository

import com.kudelich.mmfapp.data.local.BsuDatabase
import com.kudelich.mmfapp.data.mapper.toGroup
import com.kudelich.mmfapp.data.mapper.toGroupEntity
import com.kudelich.mmfapp.data.remote.api.GroupApi
import com.kudelich.mmfapp.domain.model.Group
import com.kudelich.mmfapp.domain.repository.GroupRepository
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepositoryImpl @Inject constructor(
    private val api: GroupApi,
    private val db: BsuDatabase
) : GroupRepository {

    private val dao = db.groupDao

    override suspend fun getGroupsByCourseId(
        fetchFromRemote: Boolean,
        courseId: Int
    ): Flow<Resource<List<Group>>> {
        return flow {
            emit(Resource.Loading(true))

            val localGroups = dao.getGroupsByCourseId(courseId)
            emit(Resource.Success(
                data = localGroups.map { it.toGroup() }
            ))

            val isDbEmpty = localGroups.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteCourses = try {
                val response = api.getGroupsByCourseId(courseId).map { it.toGroup() }
                response
            } catch (e: UnknownHostException){
                emit(Resource.Error("Нет интернет соединения"))
                null
            }catch (e: HttpException) {
                emit(Resource.Error("Работа сервиса временно приостановлена"))
                null
            } catch (e: IOException) {
                emit(Resource.Error("Ошибка"))
                null
            }

            remoteCourses?.let { groups ->
                dao.insertGroups(
                    groups.map { it.toGroupEntity() }
                )
                emit(
                    Resource.Success<List<Group>>(
                        data = dao.getGroupsByCourseId(courseId)
                            .map { it.toGroup() }
                    )
                )
                emit(Resource.Error(message = ""))
                emit(Resource.Loading<List<Group>>(false))
            }
        }
    }
}