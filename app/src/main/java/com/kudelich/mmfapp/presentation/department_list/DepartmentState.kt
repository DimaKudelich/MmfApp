package com.kudelich.mmfapp.presentation.department_list

import com.kudelich.mmfapp.domain.model.Department

data class DepartmentState(
    val departments: List<Department> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)