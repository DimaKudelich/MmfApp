package com.kudelich.mmfapp.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Schedule
import com.kudelich.mmfapp.presentation.views.navigation.bottom.BottomNavItem

object Constants {
    const val BASE_URL = "https://bsu-server-app.herokuapp.com"

    val BOTTOM_ITEMS = listOf(
        BottomNavItem(
            name = "Расписание",
            route = "courses",
            icon = Icons.Default.Schedule
        ),
        BottomNavItem(
            name = "Преподаватели",
            route = "departments",
            icon = Icons.Default.PermIdentity
        ),
        BottomNavItem(
            name = "Мое расписание",
            route = "userLessons",
            icon = Icons.Default.FavoriteBorder
        ),
    )
}