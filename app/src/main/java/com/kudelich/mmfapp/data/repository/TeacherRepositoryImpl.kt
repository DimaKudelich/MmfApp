package com.kudelich.mmfapp.data.repository

import com.kudelich.mmfapp.data.local.BsuDatabase
import com.kudelich.mmfapp.data.mapper.toTeacher
import com.kudelich.mmfapp.data.mapper.toTeacherEntity
import com.kudelich.mmfapp.data.remote.api.TeacherApi
import com.kudelich.mmfapp.domain.model.Teacher
import com.kudelich.mmfapp.domain.repository.TeacherRepository
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherRepositoryImpl @Inject constructor(
    private val api: TeacherApi,
    private val db: BsuDatabase
) : TeacherRepository {
    private val dao = db.teacherDao

    override suspend fun getTeacherByDepartmentId(
        fetchFromRemote: Boolean,
        departmentId: Int
    ): Flow<Resource<List<Teacher>>> {
        return flow {
            emit(Resource.Loading(true))

            val localTeachers = dao.getTeachersByDepartmentId(departmentId)
            emit(Resource.Success(
                data = localTeachers.map { it.toTeacher() }
            ))

            val isDbEmpty = localTeachers.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteTeachers = try {
                val response = api.getTeachersByDepartmentId(departmentId).map { it.toTeacher() }
                response
            }catch (e: UnknownHostException){
                emit(Resource.Error("Нет интернет соединения"))
                null
            }catch (e: HttpException) {
                emit(Resource.Error("Работа сервиса временно приостановлена"))
                null
            } catch (e: IOException) {
                emit(Resource.Error("Ошибка"))
                null
            }

            remoteTeachers?.let { lessons ->
                dao.insertTeachers(
                    lessons.map { it.toTeacherEntity() }
                )
                emit(
                    Resource.Success<List<Teacher>>(
                        data = dao.getTeachersByDepartmentId(departmentId)
                            .map { it.toTeacher() }
                    )
                )
                emit(Resource.Error(message = ""))
                emit(Resource.Loading<List<Teacher>>(false))
            }
        }
    }

    override suspend fun getTeacherByString(
        searchString: String
    ): Flow<Resource<List<Teacher>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteTeachers = try {
                val response = api.getTeachersByString(searchString).map { it.toTeacher() }
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

            remoteTeachers?.let { lessons ->
                emit(
                    Resource.Success<List<Teacher>>(
                        data = remoteTeachers
                    )
                )
                emit(Resource.Error(message = ""))
                emit(Resource.Loading<List<Teacher>>(false))
            }
        }
    }

    override suspend fun getUnknownTeachersByString(
        searchString: String
    ): Flow<Resource<List<String>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteTeachers = try {
                val response = api.getUnknownTeachersByString(searchString)
                response
            }  catch (e: UnknownHostException){
                emit(Resource.Error("Нет интернет соединения"))
                null
            }catch (e: HttpException) {
                emit(Resource.Error("Работа сервиса временно приостановлена"))
                null
            } catch (e: IOException) {
                emit(Resource.Error("Ошибка"))
                null
            }

            remoteTeachers?.let { lessons ->
                emit(
                    Resource.Success<List<String>>(
                        data = remoteTeachers
                    )
                )
                emit(Resource.Error(message = ""))
                emit(Resource.Loading<List<String>>(false))
            }
        }
    }
}