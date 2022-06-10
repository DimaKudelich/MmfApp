package com.kudelich.mmfapp.data.local.group

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kudelich.mmfapp.data.local.course.CourseEntity

@Entity(
    tableName = "groups",
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("courseId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class GroupEntity(
    val name: String,
    val courseId: Int,
    @PrimaryKey val id: Int? = null
)
