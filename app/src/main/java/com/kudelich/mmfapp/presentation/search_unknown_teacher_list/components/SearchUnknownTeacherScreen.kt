package com.kudelich.mmfapp.presentation.search_unknown_teacher_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kudelich.mmfapp.domain.model.Teacher
import com.kudelich.mmfapp.prefs
import com.kudelich.mmfapp.presentation.search_unknown_teacher_list.SearchUnknownTeacherViewModel
import com.kudelich.mmfapp.presentation.teachers_list.TeacherEvent
import com.kudelich.mmfapp.presentation.teachers_list.components.TeacherItem
import com.kudelich.mmfapp.presentation.views.navigation.Screen
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun SearchUnknownTeacherScreen(
    navController: NavController,
    viewModel: SearchUnknownTeacherViewModel = hiltViewModel()
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

        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(TeacherEvent.Refresh)
        }) {
            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.teachers.isEmpty(),
                type = "Center"
            )

            if (state.searchQuery != "" && state.teachers.isEmpty() && !state.isLoading){
                Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.TopCenter){
                    Text(
                        text = "Преподаватель не наден",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.teachers.size) { i ->
                    val teacher = state.teachers[i]

                    TeacherItem(
                        teacher = Teacher(1, teacher, 1),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                prefs.selectedTeacher = teacher
                                navController.navigate(
                                    Screen.LessonByTeacher.createRoute(teacher)
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