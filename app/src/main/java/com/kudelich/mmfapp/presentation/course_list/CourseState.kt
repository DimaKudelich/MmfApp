package com.kudelich.mmfapp.presentation.course_list

import com.kudelich.mmfapp.domain.model.Course

data class CourseState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)
