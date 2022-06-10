package com.kudelich.mmfapp.presentation.course_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kudelich.mmfapp.prefs
import com.kudelich.mmfapp.presentation.course_list.CourseEvent
import com.kudelich.mmfapp.presentation.course_list.CourseViewModel
import com.kudelich.mmfapp.presentation.views.navigation.Screen
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(start = true)
fun CourseListScreen(
    navController: NavController,
    viewModel: CourseViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {

        StatusBlock(
            isLoading = state.isLoading,
            errorMessage = state.error,
            isDataEmpty = state.courses.isEmpty(),
            type = "Top"
        )

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(CourseEvent.Refresh)
            }
        ) {

            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.courses.isEmpty(),
                type = "Center"
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.courses.size) { i ->
                    val course = state.courses[i]

                    if (i == 0 || course.type != state.courses[i - 1].type) {
                        TypeDivider(type = course.type)

                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    }

                    CourseItem(
                        course = course,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                prefs.selectedCourse = course.name
                                navController.navigate(
                                    Screen.GroupScr.createRoute(course.id)
                                )
                            }
                            .padding(16.dp)
                    )

                    if (i < state.courses.size) {
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