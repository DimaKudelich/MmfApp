package com.kudelich.mmfapp.presentation.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kudelich.mmfapp.presentation.course_list.components.CourseListScreen
import com.kudelich.mmfapp.presentation.department_list.components.DepartmentListScreen
import com.kudelich.mmfapp.presentation.group_list.components.GroupListScreen
import com.kudelich.mmfapp.presentation.lesson_by_teacher_list.components.LessonByTeacherScreen
import com.kudelich.mmfapp.presentation.lesson_list.components.LessonListScreen
import com.kudelich.mmfapp.presentation.search_teacher_list.components.SearchTeacherScreen
import com.kudelich.mmfapp.presentation.search_unknown_teacher_list.components.SearchUnknownTeacherScreen
import com.kudelich.mmfapp.presentation.teachers_list.components.TeacherListScreen
import com.kudelich.mmfapp.presentation.user_lessons_list.UserLessonsScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.CourseScr.route) {
        composable(Screen.CourseScr.route) {
            CourseListScreen(navController = navController)
        }

        composable(Screen.GroupScr.route) {
            GroupListScreen(navController = navController)
        }

        composable(Screen.LessonScr.route) {
            LessonListScreen(navController = navController)
        }

        composable(Screen.DepartmentScr.route) {
            DepartmentListScreen(navController = navController)
        }

        composable(Screen.LessonByTeacher.route) {
            LessonByTeacherScreen(navController = navController)
        }

        composable(Screen.TeachersScr.route) {
            TeacherListScreen(navController = navController)
        }

        composable(Screen.UserLessons.route) {
            UserLessonsScreen(navController = navController)
        }

        composable(Screen.TeacherSearchScr.route) {
            SearchTeacherScreen(navController = navController)
        }

        composable(Screen.UnknownTeacherSearchScreen.route) {
            SearchUnknownTeacherScreen(navController = navController)
        }
    }
}