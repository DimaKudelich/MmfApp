package com.kudelich.mmfapp.presentation.views.navigation.top

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kudelich.mmfapp.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomBarViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(SavedGroupState())

    fun getTitle(route: String?): String {
        state = state.copy(prefs.currentGroup == prefs.savedGroup)

        return when {
            route?.contains("groups") == true -> "Группы: ${prefs.selectedCourse}"
            route?.contains("lessons") == true -> "Занятия: ${prefs.selectedCourse} ${prefs.selectedGroup}"
            route?.contains("teachersSearch") == true -> "Поиск преподавателей"
            route?.contains("unknown") == true -> "Расширенный поиск"
            route?.contains("teachers") == true -> prefs.selectedDepartment
            route?.contains("departments") == true -> "Кафедры"
            route?.contains("user") == true -> "Мои занятия: ${prefs.savedCourseAndGroup}"
            route?.contains("teacherSearch") == true -> "Занятия: ${prefs.selectedTeacher}"

            else -> "Курсы"
        }
    }

    fun getNavigationIcon(route: String?): Int {
        return when {
            route?.contains("groups") == true ||
                    route?.contains("lessons") == true ||
                    route?.contains("teachers") == true ||
                    route?.contains("teachersSearch") == true ||
                    route?.contains("teacherSearch") == true ||
                    route?.contains("unknown") == true
            -> 1
            else -> 2
        }
    }

    fun getActionIcon(route: String?): Int {
        return when {
            route?.contains("departments") == true -> 1
            route?.contains("lessons") == true -> {
                if (state.isEquals) 3 else 2
            }
            else -> 4
        }
    }

    fun saveNewGroup() {
        prefs.savedGroup = prefs.currentGroup
        prefs.savedCourseAndGroup = prefs.selectedCourse + " " + prefs.selectedGroup
        state = state.copy(isEquals = true)
    }

    fun deleteSavedGroup() {
        prefs.savedGroup = -1
        prefs.savedCourseAndGroup = ""
        state = state.copy(isEquals = false)
    }
}