package com.kudelich.mmfapp.presentation.search_teacher_list

import com.kudelich.mmfapp.domain.model.Teacher

data class SearchTeacherState(
    val teachers: List<Teacher> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = "",
    val searchQuery: String = ""
)