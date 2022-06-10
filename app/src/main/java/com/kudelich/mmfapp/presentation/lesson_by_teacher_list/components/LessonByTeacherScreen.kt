package com.kudelich.mmfapp.presentation.lesson_by_teacher_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kudelich.mmfapp.presentation.lesson_by_teacher_list.LessonByTeacherEvent
import com.kudelich.mmfapp.presentation.lesson_by_teacher_list.LessonByTeacherViewModel
import com.kudelich.mmfapp.presentation.lesson_list.components.DayDivider
import com.kudelich.mmfapp.presentation.lesson_list.components.LessonItem
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun LessonByTeacherScreen(
    navController: NavController,
    viewModel: LessonByTeacherViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {

        StatusBlock(
            isLoading = state.isLoading,
            errorMessage = state.error,
            isDataEmpty = state.lessons.isEmpty(),
            type = "Top"
        )

        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(LessonByTeacherEvent.Refresh)
        }) {
            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.lessons.isEmpty(),
                type = "Center"
            )

            if (state.lessons.isEmpty()&& !state.isLoading){
                Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.TopCenter){
                    Text(
                        text = "У данного преподавателя нет занятий",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.lessons.size) { i ->
                    val lesson = state.lessons[i]
                    val isClicked = remember { mutableStateOf(false) }

                    if (i == 0) {
                        DayDivider(day = state.lessons[i].day)

                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    } else if (state.lessons[i].day != state.lessons[i - 1].day) {
                        DayDivider(day = state.lessons[i].day)

                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    }

                    LessonItem(
                        lesson = lesson,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isClicked.value = !isClicked.value
                            }
                            .padding(8.dp),
                        isClicked,
                        false
                    )

                    if (i < state.lessons.size) {
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
