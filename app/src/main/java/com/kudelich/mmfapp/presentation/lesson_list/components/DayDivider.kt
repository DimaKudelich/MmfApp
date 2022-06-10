package com.kudelich.mmfapp.presentation.lesson_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DayDivider(
    day: String
) {
    Text(
        text = getDayFromString(day),
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

fun getDayFromString(day: String): String {
    return when (day) {
        "1" -> "Понедельник"
        "2" -> "Вторник"
        "3" -> "Среда"
        "4" -> "Четверг"
        "5" -> "Пятница"
        "6" -> "Суббота"
        else -> day
    }
}