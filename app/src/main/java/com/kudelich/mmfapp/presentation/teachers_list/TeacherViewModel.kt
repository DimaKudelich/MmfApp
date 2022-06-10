package com.kudelich.mmfapp.presentation.teachers_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudelich.mmfapp.domain.repository.TeacherRepository
import com.kudelich.mmfapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TeacherRepository
) : ViewModel() {
    var state by mutableStateOf(TeacherState())
    var departmentId = savedStateHandle.get<String>("departmentId")?.toInt()

    init {
        departmentId?.let { getTeachers(departmentId = it) }
    }

    fun onEvent(event: TeacherEvent) {
        when (event) {
            is TeacherEvent.Refresh -> {
                getTeachers(fetchFromRemote = true, departmentId = departmentId!!)
            }
        }
    }

    private fun getTeachers(
        fetchFromRemote: Boolean = false,
        departmentId: Int
    ) {
        viewModelScope.launch {
            repository.getTeacherByDepartmentId(fetchFromRemote, departmentId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { teachers ->
                            state = state.copy(
                                teachers = teachers
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