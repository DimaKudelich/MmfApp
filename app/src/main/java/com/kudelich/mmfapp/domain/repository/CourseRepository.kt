package com.kudelich.mmfapp.domain.repository

import com.kudelich.mmfapp.domain.model.Course
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourses(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<Course>>>
}

