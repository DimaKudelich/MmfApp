package com.kudelich.mmfapp.data.remote.dto

data class LessonDto(
    val auditorium: String,
    val groupId: Int,
    val id: Int,
    val name: String,
    val teacher: String,
    val time: String,
    val type: String,
    val day: String,
    val subgroup: String
)
