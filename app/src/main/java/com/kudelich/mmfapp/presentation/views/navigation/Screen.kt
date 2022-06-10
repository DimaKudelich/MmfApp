package com.kudelich.mmfapp.presentation.views.navigation


sealed class Screen(val route: String) {
    object CourseScr : Screen("courses")

    object GroupScr : Screen("course/{courseId}/groups") {
        fun createRoute(courseId: Int) = "course/$courseId/groups"
    }

    object LessonScr : Screen("course/{groupId}/lessons") {
        fun createRoute(groupId: Int) = "course/$groupId/lessons"
    }

    object DepartmentScr : Screen("departments")

    object TeachersScr : Screen("department/{departmentId}/teachers") {
        fun createRoute(departmentId: Int) = "department/$departmentId/teachers"
    }

    object TeacherSearchScr : Screen("department/teachersSearch")

    object UnknownTeacherSearchScreen : Screen("department/unknownTeacherSearch")

    object LessonByTeacher : Screen("department/teacherSearch/{teacherName}") {
        fun createRoute(teacherName: String) = "department/teacherSearch/$teacherName"
    }

    object UserLessons : Screen("userLessons")
}