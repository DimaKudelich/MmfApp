package com.kudelich.mmfapp.data.mapper

import com.kudelich.mmfapp.data.local.lesson.LessonEntity
import com.kudelich.mmfapp.data.remote.dto.LessonDto
import com.kudelich.mmfapp.domain.model.Lesson

fun LessonDto.toLesson(): Lesson {
    return Lesson(
        id = id,
        name = name,
        teacher = teacher,
        time = time,
        auditorium = auditorium,
        groupId = groupId,
        type = type,
        day = day,
        subgroup = subgroup
    )
}

fun LessonEntity.toLesson(): Lesson {
    return Lesson(
        id = id,
        name = name,
        teacher = teacher,
        time = time,
        auditorium = auditorium,
        groupId = groupId,
        type = type,
        day = day,
        subgroup = subgroup
    )
}

fun Lesson.toLessonEntity(): LessonEntity {
    return LessonEntity(
        id = id,
        name = name,
        teacher = teacher,
        time = time,
        auditorium = auditorium,
        groupId = groupId,
        type = type,
        day = day,
        subgroup = subgroup
    )
}