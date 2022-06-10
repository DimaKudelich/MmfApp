package com.kudelich.mmfapp.presentation.group_list

import com.kudelich.mmfapp.domain.model.Group


data class GroupState(
    val groups: List<Group> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)
