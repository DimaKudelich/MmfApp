package com.kudelich.mmfapp.presentation.course_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.CourseRepository
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {
    var state by mutableStateOf(CourseState())

    init {
        getCourses()
    }

    fun onEvent(event: CourseEvent) {
        when (event) {
            is CourseEvent.Refresh -> {
                getCourses(fetchFromRemote = true)
            }
        }
    }

    private fun getCourses(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getCourses(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { courses ->
                            state = state.copy(
                                courses = courses
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