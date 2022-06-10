package com.kudelich.mmfapp.presentation.department_list.components

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
import com.kudelich.mmfapp.presentation.department_list.DepartmentEvent
import com.kudelich.mmfapp.presentation.department_list.DepartmentViewModel
import com.kudelich.mmfapp.presentation.views.navigation.Screen
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun DepartmentListScreen(
    navController: NavController,
    viewModel: DepartmentViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {

        StatusBlock(
            isLoading = state.isLoading,
            errorMessage = state.error,
            isDataEmpty = state.departments.isEmpty(),
            type = "Top"
        )

        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(DepartmentEvent.Refresh)
        }) {
            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.departments.isEmpty(),
                type = "Center"
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.departments.size) { i ->
                    val department = state.departments[i]

                    DepartmentItem(
                        department = department,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                prefs.selectedDepartment = department.name
                                navController.navigate(
                                    Screen.TeachersScr.createRoute(department.id)
                                )
                            }
                            .padding(8.dp)
                    )

                    if (i < state.departments.size) {
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