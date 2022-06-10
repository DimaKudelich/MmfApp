package com.kudelich.mmfapp.data.repository

import com.kudelich.mmfapp.data.local.BsuDatabase
import com.kudelich.mmfapp.data.mapper.toCourse
import com.kudelich.mmfapp.data.mapper.toCourseEntity
import com.kudelich.mmfapp.data.remote.api.CourseApi
import com.kudelich.mmfapp.domain.model.Course
import com.kudelich.mmfapp.domain.repository.CourseRepository
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val db: BsuDatabase
) : CourseRepository {

    private val dao = db.courseDao

    override suspend fun getCourses(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Course>>> = flow {
        emit(Resource.Loading(true))

        val localCourses = dao.getCourses()
        emit(Resource.Success(
            data = localCourses.map { it.toCourse() }
        ))

        val isDbEmpty = localCourses.isEmpty()
        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

        if (shouldJustLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }

        val remoteCourses = try {
            val response = api.getCourses().map { it.toCourse() }
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

        remoteCourses?.let { courses ->
            dao.insertCourses(
                courses.map { it.toCourseEntity() }
            )
            emit(
                Resource.Success<List<Course>>(
                    data = dao
                        .getCourses()
                        .map { it.toCourse() },
                )
            )
            emit(Resource.Error(message = ""))
            emit(Resource.Loading<List<Course>>(false))
        }
    }
}