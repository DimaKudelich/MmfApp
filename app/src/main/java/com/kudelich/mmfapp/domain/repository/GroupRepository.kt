package com.kudelich.mmfapp.domain.repository

import com.kudelich.mmfapp.domain.model.Group
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    suspend fun getGroupsByCourseId(
        fetchFromRemote: Boolean,
        courseId: Int
    ): Flow<Resource<List<Group>>>
}