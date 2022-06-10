package com.kudelich.mmfapp.presentation.group_list.components

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
import com.kudelich.mmfapp.presentation.group_list.GroupEvent
import com.kudelich.mmfapp.presentation.group_list.GroupViewModel
import com.kudelich.mmfapp.presentation.views.navigation.Screen
import com.kudelich.mmfapp.presentation.views.status.StatusBlock
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination()
fun GroupListScreen(
    navController: NavController,
    viewModel: GroupViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {

        StatusBlock(
            isLoading = state.isLoading,
            errorMessage = state.error,
            isDataEmpty = state.groups.isEmpty(),
            type = "Top"
        )

        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(GroupEvent.Refresh)
        }) {

            StatusBlock(
                isLoading = state.isLoading,
                errorMessage = state.error,
                isDataEmpty = state.groups.isEmpty(),
                type = "Center"
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.groups.size) { i ->
                    val group = state.groups[i]

                    GroupItem(
                        group = group,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                prefs.selectedGroup = group.name
                                navController.navigate(
                                    Screen.LessonScr.createRoute(group.id)
                                )
                            }
                            .padding(16.dp)
                    )

                    if (i < state.groups.size) {
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