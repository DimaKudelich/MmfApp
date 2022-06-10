package com.kudelich.mmfapp.presentation.user_lessons_list

import com.kudelich.mmfapp.domain.model.Lesson

data class UserLessonState(
    val lessons: List<Lesson> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = "",
    val isEmptyGroup: Boolean = true
) {
}
