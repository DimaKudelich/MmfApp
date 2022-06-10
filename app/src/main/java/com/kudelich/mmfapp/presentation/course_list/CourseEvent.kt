package com.kudelich.mmfapp.presentation.course_list

sealed class CourseEvent {
    object Refresh : CourseEvent()
}