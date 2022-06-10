package com.kudelich.mmfapp.presentation.lesson_by_teacher_list

import com.kudelich.mmfapp.domain.model.Lesson

data class LessonByTeacherState(
    val lessons: List<Lesson> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
) {
}