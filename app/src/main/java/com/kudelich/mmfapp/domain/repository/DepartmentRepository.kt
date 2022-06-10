package com.kudelich.mmfapp.domain.repository

import com.kudelich.mmfapp.domain.model.Department
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {
    suspend fun getDepartments(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Department>>>
}