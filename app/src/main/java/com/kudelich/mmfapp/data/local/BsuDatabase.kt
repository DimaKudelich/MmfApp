package com.kudelich.mmfapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudelich.mmfapp.data.local.course.CourseDao
import com.kudelich.mmfapp.data.local.course.CourseEntity
import com.kudelich.mmfapp.data.local.department.DepartmentDao
import com.kudelich.mmfapp.data.local.department.DepartmentEntity
import com.kudelich.mmfapp.data.local.group.GroupDao
import com.kudelich.mmfapp.data.local.group.GroupEntity
import com.kudelich.mmfapp.data.local.lesson.LessonDao
import com.kudelich.mmfapp.data.local.lesson.LessonEntity
import com.kudelich.mmfapp.data.local.teacher.TeacherDao
import com.kudelich.mmfapp.data.local.teacher.TeacherEntity

@Database(
    entities = [
        CourseEntity::class,
        GroupEntity::class,
        LessonEntity::class,
        DepartmentEntity::class,
        TeacherEntity::class
    ],
    version = 1
)
abstract class BsuDatabase : RoomDatabase() {
    abstract val courseDao: CourseDao
    abstract val groupDao: GroupDao
    abstract val lessonDao: LessonDao
    abstract val departmentDao: DepartmentDao
    abstract val teacherDao: TeacherDao
}
