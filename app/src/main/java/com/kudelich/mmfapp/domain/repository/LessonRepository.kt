package com.kudelich.mmfapp.domain.repository

import com.kudelich.mmfapp.domain.model.Lesson
import com.kudelich.mmfapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    suspend fun getLessonsByGroupId(
        fetchFromRemote: Boolean,
        groupId: Int
    ): Flow<Resource<List<Lesson>>>

    suspend fun getLessonsByTeacher(teacher: String): Flow<Resource<List<Lesson>>>
}