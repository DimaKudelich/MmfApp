package com.kudelich.mmfapp.data.remote.api

import com.kudelich.mmfapp.data.remote.dto.CourseDto
import retrofit2.http.GET

interface CourseApi {
    @GET("/courses/all")
    suspend fun getCourses(): List<CourseDto>
}

