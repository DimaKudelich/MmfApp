package com.kudelich.mmfapp.presentation.search_teacher_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kudelich.mmfapp.prefs
import com.kudelich.mmfapp.presentation.search_teacher_list.SearchTeacherViewModel
import com.kudelich.mmfapp.presentation.teachers_list.TeacherEvent
import com.kudelich.mmfapp.presentation.teachers_list.components.TeacherItem
import com.kudelich.mmfapp.presentation.views.navigation.Screen
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun SearchTeacherScreen(
    navController: NavController,
    viewModel: SearchTeacherViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(TeacherEvent.OnSearchQueryChange(it)) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true
        )

        StatusBlock(
            isLoading = state.isLoading,
            errorMessage = state.error,
            isDataEmpty = state.teachers.isEmpty(),
            type = "Top"
        )

        if (state.searchQuery != "" && state.teachers.size == 0 && state.isLoading == false) {
            EmptyResultBlock(navController)
        }

        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(TeacherEvent.Refresh)
        }) {
            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.teachers.isEmpty(),
                type = "Center"
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (state.teachers.size != 0) {
                    items(state.teachers.size) { i ->
                        val teacher = state.teachers[i]

                        TeacherItem(
                            teacher = teacher,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    prefs.selectedTeacher = teacher.name
                                    navController.navigate(
                                        Screen.LessonByTeacher.createRoute(teacher.name)
                                    )
                                }
                                .padding(8.dp)
                        )

                        if (i < state.teachers.size) {
                            Divider(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}