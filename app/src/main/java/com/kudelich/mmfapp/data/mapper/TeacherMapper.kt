package com.kudelich.mmfapp.data.mapper

import com.kudelich.mmfapp.data.local.teacher.TeacherEntity
import com.kudelich.mmfapp.data.remote.dto.TeacherDto
import com.kudelich.mmfapp.domain.model.Teacher

fun TeacherDto.toTeacherEntity(): TeacherEntity {
    return TeacherEntity(
        id = id,
        name = name,
        departmentId = departmentId
    )
}

fun TeacherEntity.toTeacher(): Teacher {
    return Teacher(
        id = id!!,
        name = name,
        departmentId = departmentId
    )
}

fun TeacherDto.toTeacher(): Teacher {
    return Teacher(
        id = id,
        name = name,
        departmentId = departmentId
    )
}

fun Teacher.toTeacherEntity(): TeacherEntity {
    return TeacherEntity(
        id = id,
        name = name,
        departmentId = departmentId
    )
}