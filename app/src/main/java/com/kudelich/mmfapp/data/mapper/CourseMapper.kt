package com.kudelich.mmfapp.data.mapper

import com.kudelich.mmfapp.data.local.course.CourseEntity
import com.kudelich.mmfapp.data.remote.dto.CourseDto
import com.kudelich.mmfapp.domain.model.Course


fun CourseDto.toCourse(): Course {
    return Course(
        id = id,
        name = name,
        type = type
    )
}

fun CourseEntity.toCourse(): Course {
    return Course(
        id = id!!,
        name = name,
        type = type
    )
}

fun Course.toCourseEntity(): CourseEntity {
    return CourseEntity(
        id = id,
        name = name,
        type = type
    )
}