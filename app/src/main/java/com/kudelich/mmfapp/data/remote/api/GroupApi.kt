package com.kudelich.mmfapp.data.remote.api

import com.kudelich.mmfapp.data.remote.dto.GroupDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupApi {
    @GET("/groups/gets/{id}")
    suspend fun getGroupsByCourseId(@Path("id") courseId: Int): List<GroupDto>
}

