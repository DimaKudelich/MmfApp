package com.kudelich.mmfapp.presentation.department_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.DepartmentRepository
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val repository: DepartmentRepository
) : ViewModel() {
    var state by mutableStateOf(DepartmentState())

    init {
        getDepartments()
    }

    fun onEvent(event: DepartmentEvent) {
        when (event) {
            is DepartmentEvent.Refresh -> {
                getDepartments(fetchFromRemote = true)
            }
        }
    }

    private fun getDepartments(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getDepartments(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { departments ->
                            state = state.copy(
                                departments = departments
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