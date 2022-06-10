package com.kudelich.mmfapp.presentation.lesson_list

sealed class LessonEvent {
    object Refresh : LessonEvent()
}