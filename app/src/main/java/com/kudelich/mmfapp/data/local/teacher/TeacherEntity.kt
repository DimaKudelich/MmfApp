package com.kudelich.mmfapp.data.local.teacher

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kudelich.mmfapp.data.local.department.DepartmentEntity

@Entity(
    tableName = "teachers",
    foreignKeys = [ForeignKey(
        entity = DepartmentEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("departmentId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TeacherEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val departmentId: Int
)
