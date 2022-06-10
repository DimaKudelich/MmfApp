package com.kudelich.mmfapp.presentation.course_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TypeDivider(
    type: Int
) {
    Text(
        text = getTypeFromInteger(type),
        modifier = Modifier.padding(15.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

fun getTypeFromInteger(type: Int): String {
    return when (type) {
        1 -> "Дневное отделение"
        2 -> "Заочное отделение"
        else -> "Непонятно"
    }
}