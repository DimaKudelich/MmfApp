package com.kudelich.mmfapp.data.repository

import com.kudelich.mmfapp.data.local.BsuDatabase
import com.kudelich.mmfapp.data.mapper.toLesson
import com.kudelich.mmfapp.data.mapper.toLessonEntity
import com.kudelich.mmfapp.data.remote.api.LessonApi
import com.kudelich.mmfapp.domain.model.Lesson
import com.kudelich.mmfapp.domain.repository.LessonRepository
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepositoryImpl @Inject constructor(
    private val api: LessonApi,
    private val db: BsuDatabase
) : LessonRepository {

    private val dao = db.lessonDao

    override suspend fun getLessonsByGroupId(
        fetchFromRemote: Boolean,
        groupId: Int
    ): Flow<Resource<List<Lesson>>> {
        return flow {
            emit(Resource.Loading(true))

            val localGroups = dao.getLessonsByGroupId(groupId)
            emit(Resource.Success(
                data = localGroups.map { it.toLesson() }
            ))

            val isDbEmpty = localGroups.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteLessons = try {
                val response = api.getLessonsByGroupId(groupId).map { it.toLesson() }
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

            remoteLessons?.let { lessons ->
                dao.insertLessons(
                    lessons.map { it.toLessonEntity() }
                )
                emit(
                    Resource.Success<List<Lesson>>(
                        data = dao.getLessonsByGroupId(groupId)
                            .map { it.toLesson() }
                    )
                )
                emit(Resource.Error(message = ""))
                emit(Resource.Loading<List<Lesson>>(false))
            }
        }
    }

    override suspend fun getLessonsByTeacher(teacher: String): Flow<Resource<List<Lesson>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteLessons = try {
                val response = api.getLessonsByTeacher(teacher).map { it.toLesson() }
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

            remoteLessons?.let { lessons ->
                emit(
                    Resource.Success<List<Lesson>>(
                        data = lessons
                    )
                )
                emit(Resource.Error(message = ""))
                emit(Resource.Loading<List<Lesson>>(false))
            }
        }
    }
}