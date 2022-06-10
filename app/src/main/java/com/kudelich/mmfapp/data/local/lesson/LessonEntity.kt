package com.kudelich.mmfapp.data.local.lesson

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kudelich.mmfapp.data.local.group.GroupEntity

@Entity(
    tableName = "lessons",
    foreignKeys = [ForeignKey(
        entity = GroupEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("groupId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class LessonEntity(
    val auditorium: String,
    val teacher: String,
    val time: String,
    val name: String,
    val groupId: Int,
    val type: String,
    val day: String,
    val subgroup: String,
    @PrimaryKey val id: Int
)