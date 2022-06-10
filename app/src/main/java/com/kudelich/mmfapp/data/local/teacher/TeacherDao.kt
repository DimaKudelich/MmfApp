package com.kudelich.mmfapp.data.local.teacher

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTeachers(
        courseEntity: List<TeacherEntity>
    )

    @Query(
        """
        SELECT *
        FROM teachers
        WHERE departmentId = :departmentId
    """
    )
    suspend fun getTeachersByDepartmentId(departmentId: Int): List<TeacherEntity>
}