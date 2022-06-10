package com.kudelich.mmfapp.data.local.department

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDepartment(
        courseEntity: List<DepartmentEntity>
    )

    @Query(
        """
        SELECT *
        FROM departments
    """
    )
    suspend fun getDepartments(): List<DepartmentEntity>
}