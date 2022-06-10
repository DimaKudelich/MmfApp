package com.kudelich.mmfapp.presentation.department_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kudelich.mmfapp.R
import com.kudelich.mmfapp.domain.model.Department

@Composable
fun DepartmentItem(
    department: Department,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = getImageFromDepartment(department.id)),
            contentDescription = "",
            modifier = Modifier.requiredSize(150.dp)
        )
        Text(
            text = department.name,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

fun getImageFromDepartment(id: Int): Int {
    return when (id) {
        1 -> R.drawable.logo_1
        2 -> R.drawable.logo_2
        3 -> R.drawable.logo_3
        4 -> R.drawable.logo_4
        5 -> R.drawable.logo_5
        6 -> R.drawable.logo_6
        7 -> R.drawable.logo_7
        8 -> R.drawable.logo_8
        9 -> R.drawable.logo_9
        10 -> R.drawable.logo_10
        else -> R.drawable.logo_1
    }
}