package com.kudelich.mmfapp.util

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val SAVED_GROUP_ID = "savedGroup"
    private val CURRENT_GROUP = "currentGroup"

    private val SAVED_COURSE_AND_GROUP = "savedCourseAndGroup"

    private val SELECTED_COURSE = "selectedCourse"
    private val SELECTED_GROUP = "selectedGroup"
    private val SELECTED_DEPARTMENT = "selectedDepartment"
    private val SELECTED_TEACHER = "selectedTeacher"

    private val preferences: SharedPreferences =
        context.getSharedPreferences("USER", Context.MODE_PRIVATE)

    var savedGroup: Int
        get() = preferences.getInt(SAVED_GROUP_ID, -1)
        set(value) = preferences.edit().putInt(SAVED_GROUP_ID, value).apply()

    var currentGroup: Int
        get() = preferences.getInt(CURRENT_GROUP, -1)
        set(value) = preferences.edit().putInt(CURRENT_GROUP, value).apply()

    var selectedCourse: String
        get() = preferences.getString(SELECTED_COURSE, "") ?: ""
        set(value) = preferences.edit().putString(SELECTED_COURSE, value).apply()

    var selectedGroup: String
        get() = preferences.getString(SELECTED_GROUP, "") ?: ""
        set(value) = preferences.edit().putString(SELECTED_GROUP, value).apply()

    var savedCourseAndGroup: String
        get() = preferences.getString(SAVED_COURSE_AND_GROUP, "") ?: ""
        set(value) = preferences.edit().putString(SAVED_COURSE_AND_GROUP, value).apply()

    var selectedDepartment: String
        get() = preferences.getString(SELECTED_DEPARTMENT, "") ?: ""
        set(value) = preferences.edit().putString(SELECTED_DEPARTMENT, value).apply()

    var selectedTeacher: String
        get() = preferences.getString(SELECTED_TEACHER, "") ?: ""
        set(value) = preferences.edit().putString(SELECTED_TEACHER, value).apply()
}