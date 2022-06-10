package com.kudelich.mmfapp.data.local.course

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "courses"
)
data class CourseEntity(
    val name: String,
    val type: Int,
    @PrimaryKey val id: Int? = null
)
