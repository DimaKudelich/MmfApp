package com.kudelich.mmfapp.data.remote.api

import com.kudelich.mmfapp.data.remote.dto.DepartmentDto
import retrofit2.http.GET

interface DepartmentApi {
    @GET("/departments/all")
    suspend fun getDepartments(): List<DepartmentDto>
}