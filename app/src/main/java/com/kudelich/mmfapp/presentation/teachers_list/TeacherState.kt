package com.kudelich.mmfapp.presentation.teachers_list

import com.kudelich.mmfapp.domain.model.Teacher

data class TeacherState(
    val teachers: List<Teacher> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)