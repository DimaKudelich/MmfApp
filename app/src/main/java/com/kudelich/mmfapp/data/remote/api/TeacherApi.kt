package com.kudelich.mmfapp.data.remote.api

import com.kudelich.mmfapp.data.remote.dto.TeacherDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TeacherApi {
    @GET("/teachers/gets/{id}")
    suspend fun getTeachersByDepartmentId(@Path("id") departmentId: Int): List<TeacherDto>

    @GET("/teachers/gets/teacher/{searchString}")
    suspend fun getTeachersByString(@Path("searchString") searchString: String): List<TeacherDto>

    @GET("/teachers/gets/unknownTeacher/{searchString}")
    suspend fun getUnknownTeachersByString(@Path("searchString") searchString: String): List<String>
}