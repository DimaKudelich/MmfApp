package com.kudelich.mmfapp.data.remote.api

import com.kudelich.mmfapp.data.remote.dto.LessonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LessonApi {
    @GET("/lessons/gets/{id}")
    suspend fun getLessonsByGroupId(
        @Path("id") groupId: Int
    ): List<LessonDto>

    @GET("lessons/gets/teacher/{teacher}")
    suspend fun getLessonsByTeacher(
        @Path("teacher") teacher: String
    ): List<LessonDto>
}

