package com.kudelich.mmfapp.presentation.group_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.GroupRepository
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: GroupRepository
) : ViewModel() {
    var state by mutableStateOf(GroupState())
    var courseId = savedStateHandle.get<String>("courseId")?.toInt()

    init {
        courseId?.let { getGroupByCourseId(it) }
    }

    fun onEvent(event: GroupEvent, courseIdForSettings: Int? = null) {
        when (event) {
            is GroupEvent.Refresh -> {
                getGroupByCourseId(courseId = courseId!!, fetchFromRemote = true)
            }

            is GroupEvent.LoadForSettings -> {
                getGroupByCourseId(courseId = courseIdForSettings!!)
            }
        }
    }

    private fun getGroupByCourseId(
        courseId: Int,
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getGroupsByCourseId(fetchFromRemote, courseId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { groups ->
                            state = state.copy(
                                groups = groups
                            )
                        }
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message ?: "Unexpected error"
                        )
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}