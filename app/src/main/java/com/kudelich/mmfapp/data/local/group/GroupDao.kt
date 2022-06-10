package com.kudelich.mmfapp.data.local.group

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGroups(
        groupEntity: List<GroupEntity>
    )

    @Query(
        """
        SELECT *
        FROM groups
        WHERE courseId = :courseId
    """
    )
    suspend fun getGroupsByCourseId(courseId: Int): List<GroupEntity>
}

