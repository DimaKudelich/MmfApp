package com.kudelich.mmfapp.presentation.lesson_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.LessonRepository
import com.kudelich.mmfapp.prefs
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: LessonRepository
) : ViewModel() {
    var state by mutableStateOf(LessonState())
    var groupId = savedStateHandle.get<String>("groupId")?.toInt()

    init {
        groupId?.let {
            getLessonsByGroupId(it)
            prefs.currentGroup = it
        }
    }

    fun onEvent(event: LessonEvent) {
        when (event) {
            is LessonEvent.Refresh -> {
                getLessonsByGroupId(groupId = groupId!!, fetchFromRemote = true)
            }
        }
    }

    private fun getLessonsByGroupId(
        groupId: Int,
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getLessonsByGroupId(fetchFromRemote, groupId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { lessons ->
                            state = state.copy(
                                lessons = lessons
                            )
                        }
                    }

                    is Resource.Error -> {
                        state = state.copy(isLoading = false, error = result.message ?: "")
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}