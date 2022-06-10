package com.kudelich.mmfapp.data.local.course

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourses(
        courseEntity: List<CourseEntity>
    )

    @Query("SELECT * FROM courses")
    suspend fun getCourses(): List<CourseEntity>
}

