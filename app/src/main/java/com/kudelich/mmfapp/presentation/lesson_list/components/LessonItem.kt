package com.kudelich.mmfapp.presentation.lesson_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kudelich.mmfapp.domain.model.Lesson

@Composable
fun LessonItem(
    lesson: Lesson,
    modifier: Modifier,
    isClicked: MutableState<Boolean>,
    isTeacherNeed: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = lesson.time,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            modifier = Modifier
                .width(60.dp)
                .padding(end = 5.dp)
        )
        Text(
            text = lesson.subgroup,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .width(30.dp)
                .padding(end = 2.dp)
        )
        Column(
            modifier = Modifier
                .width(230.dp)
                .padding(end = 10.dp)
        ) {
            Text(
                text = lesson.name,
                style = MaterialTheme.typography.body1,
                maxLines = if (!isClicked.value) 1 else Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis
            )
            if (isTeacherNeed) {
                Text(
                    text = lesson.teacher,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (!isClicked.value) 1 else Int.MAX_VALUE,
                )
            }
        }
        Text(
            text = lesson.type,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .width(25.dp)
                .padding(end = 10.dp)
        )
        Text(
            text = lesson.auditorium,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = if (!isClicked.value) 2 else Int.MAX_VALUE,
        )
    }
}