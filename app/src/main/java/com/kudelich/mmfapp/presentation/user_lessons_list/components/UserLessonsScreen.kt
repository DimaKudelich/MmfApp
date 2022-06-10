package com.kudelich.mmfapp.presentation.user_lessons_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kudelich.mmfapp.presentation.lesson_list.LessonEvent
import com.kudelich.mmfapp.presentation.lesson_list.components.DayDivider
import com.kudelich.mmfapp.presentation.lesson_list.components.LessonItem
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun UserLessonsScreen(
    navController: NavController,
    viewModel: UserLessonViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    if (state.isEmptyGroup)
        StatusBlock(
            isLoading = state.isLoading,
            errorMessage = "Вы еще не выбрали свою группу. " +
                    "Перейдите на страницу \"Расписание\" и выберите, какое расписание хотите сохранить",
            isDataEmpty = state.lessons.isEmpty(),
            type = "Center"
        )
    else
        Column(modifier = Modifier.fillMaxSize()) {

            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.lessons.isEmpty(),
                type = "Top"
            )

            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                viewModel.onEvent(LessonEvent.Refresh)
            }) {
                StatusBlock(
                    isLoading = state.isLoading,
                    errorMessage = state.error,
                    isDataEmpty = state.lessons.isEmpty(),
                    type = "Center"
                )

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
                            true
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