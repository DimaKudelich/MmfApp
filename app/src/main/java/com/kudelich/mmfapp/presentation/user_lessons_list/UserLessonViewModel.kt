package com.kudelich.mmfapp.presentation.user_lessons_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.LessonRepository
import com.kudelich.mmfapp.prefs
import com.kudelich.mmfapp.presentation.lesson_list.LessonEvent
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLessonViewModel @Inject constructor(
    private val repository: LessonRepository
) : ViewModel() {
    var state by mutableStateOf(UserLessonState())
    var groupId = prefs.savedGroup

    init {
        if (groupId == -1) {
            state = state.copy(isEmptyGroup = true)
        } else {
            state = state.copy(isEmptyGroup = false)
            groupId.let {
                getLessonsByGroupId(it)
            }
        }
    }

    fun onEvent(event: LessonEvent) {
        when (event) {
            is LessonEvent.Refresh -> {
                getLessonsByGroupId(groupId = groupId, fetchFromRemote = true)
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