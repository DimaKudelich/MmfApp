package com.kudelich.mmfapp.presentation.search_unknown_teacher_list

data class SearchUnknownTeacherState(
    val teachers: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = "",
    val searchQuery: String = ""
)