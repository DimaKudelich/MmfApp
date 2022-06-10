package com.kudelich.mmfapp.data.repository

import com.kudelich.mmfapp.data.local.BsuDatabase
import com.kudelich.mmfapp.data.mapper.toDepartment
import com.kudelich.mmfapp.data.mapper.toDepartmentEntity
import com.kudelich.mmfapp.data.remote.api.DepartmentApi
import com.kudelich.mmfapp.domain.model.Department
import com.kudelich.mmfapp.domain.repository.DepartmentRepository
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DepartmentRepositoryImpl @Inject constructor(
    private val api: DepartmentApi,
    private val db: BsuDatabase
) : DepartmentRepository {

    private val dao = db.departmentDao

    override suspend fun getDepartments(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Department>>> = flow {
        emit(Resource.Loading(true))

        val localDepartments = dao.getDepartments()
        emit(Resource.Success(
            data = localDepartments.map { it.toDepartment() }
        ))

        val isDbEmpty = localDepartments.isEmpty()
        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

        if (shouldJustLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }

        val remoteDepartments = try {
            val response = api.getDepartments().map { it.toDepartment() }
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

        remoteDepartments?.let { departments ->
            dao.insertDepartment(
                departments.map { it.toDepartmentEntity() }
            )
            emit(
                Resource.Success<List<Department>>(
                    data = dao
                        .getDepartments()
                        .map { it.toDepartment() }
                )
            )
            emit(Resource.Error(message = ""))
            emit(Resource.Loading<List<Department>>(false))
        }
    }
}