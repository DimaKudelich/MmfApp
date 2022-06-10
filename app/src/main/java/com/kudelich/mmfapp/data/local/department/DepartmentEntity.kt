package com.kudelich.mmfapp.data.local.department

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "departments"
)
data class DepartmentEntity(
    val name: String,
    @PrimaryKey val id: Int
)
