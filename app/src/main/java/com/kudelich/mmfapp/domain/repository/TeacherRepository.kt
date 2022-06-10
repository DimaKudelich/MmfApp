package com.kudelich.mmfapp.domain.repository

import com.kudelich.mmfapp.domain.model.Teacher
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface TeacherRepository {
    suspend fun getTeacherByDepartmentId(
        fetchFromRemote: Boolean,
        departmentId: Int
    ): Flow<Resource<List<Teacher>>>

    suspend fun getTeacherByString(
        searchString: String
    ): Flow<Resource<List<Teacher>>>

    suspend fun getUnknownTeachersByString(
        searchString: String
    ): Flow<Resource<List<String>>>
}

