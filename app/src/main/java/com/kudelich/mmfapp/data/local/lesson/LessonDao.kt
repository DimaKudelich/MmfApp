package com.kudelich.mmfapp.data.local.lesson

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLessons(
        groupEntity: List<LessonEntity>
    )

    @Query(
        """
        SELECT *
        FROM lessons
        WHERE groupId = :groupId
    """
    )
    suspend fun getLessonsByGroupId(groupId: Int): List<LessonEntity>
}