package com.kudelich.mmfapp.presentation.teachers_list

sealed class TeacherEvent {
    object Refresh : TeacherEvent()
    data class OnSearchQueryChange(val query: String) : TeacherEvent()
}