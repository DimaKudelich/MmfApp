package com.kudelich.mmfapp.presentation.lesson_by_teacher_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.LessonRepository
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonByTeacherViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: LessonRepository
) : ViewModel() {
    var state by mutableStateOf(LessonByTeacherState())
    var teacherName = savedStateHandle.get<String>("teacherName")

    init {
        teacherName?.let { getLessonsByTeacher(it) }
    }

    fun onEvent(event: LessonByTeacherEvent) {
        when (event) {
            is LessonByTeacherEvent.Refresh -> {
                getLessonsByTeacher(teacherName = teacherName!!)
            }
        }
    }

    private fun getLessonsByTeacher(
        teacherName: String
    ) {
        viewModelScope.launch {
            repository.getLessonsByTeacher(teacherName).collect { result ->
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
