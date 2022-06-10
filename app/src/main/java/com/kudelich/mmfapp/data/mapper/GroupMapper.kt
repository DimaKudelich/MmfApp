package com.kudelich.mmfapp.data.mapper

import com.kudelich.mmfapp.data.local.group.GroupEntity
import com.kudelich.mmfapp.data.remote.dto.GroupDto
import com.kudelich.mmfapp.domain.model.Group

fun GroupDto.toGroupEntity(): GroupEntity {
    return GroupEntity(
        id = id,
        name = name,
        courseId = courseId
    )
}

fun GroupEntity.toGroup(): Group {
    return Group(
        id = id!!,
        name = name,
        courseId = courseId
    )
}

fun GroupDto.toGroup(): Group {
    return Group(
        id = id,
        name = name,
        courseId = courseId
    )
}

fun Group.toGroupEntity(): GroupEntity {
    return GroupEntity(
        id = id,
        name = name,
        courseId = courseId
    )
}