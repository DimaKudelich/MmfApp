package com.kudelich.mmfapp.presentation.search_unknown_teacher_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.TeacherRepository
import com.kudelich.mmfapp.presentation.teachers_list.TeacherEvent
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUnknownTeacherViewModel @Inject constructor(
    private val repository: TeacherRepository
) : ViewModel() {
    var state by mutableStateOf(SearchUnknownTeacherState())
    private var searchJob: Job? = null

    fun onEvent(event: TeacherEvent) {
        when (event) {
            is TeacherEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    getUnknownTeachersByString(state.searchQuery.lowercase())
                }
            }
        }
    }

    private fun getUnknownTeachersByString(query: String = state.searchQuery.lowercase()) {
        viewModelScope.launch {
            repository.getUnknownTeachersByString(query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { teachers ->
                            state = state.copy(
                                teachers = teachers
                            )
                        }
                    }

                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}