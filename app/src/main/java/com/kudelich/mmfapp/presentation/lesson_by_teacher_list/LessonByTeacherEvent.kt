package com.kudelich.mmfapp.presentation.lesson_by_teacher_list

sealed class LessonByTeacherEvent {
    object Refresh : LessonByTeacherEvent()
}