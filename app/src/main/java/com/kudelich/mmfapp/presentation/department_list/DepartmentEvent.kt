package com.kudelich.mmfapp.presentation.department_list

sealed class DepartmentEvent {
    object Refresh : DepartmentEvent()
}