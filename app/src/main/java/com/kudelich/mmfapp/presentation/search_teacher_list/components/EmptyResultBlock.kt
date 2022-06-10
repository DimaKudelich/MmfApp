package com.kudelich.mmfapp.presentation.search_teacher_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kudelich.mmfapp.presentation.views.navigation.Screen

@Composable
fun EmptyResultBlock(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Поиск не дал результатов. " +
                    "Можете воспользоваться",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.UnknownTeacherSearchScreen.route
                )
            }
        ) {
            Text(
                text = "расширенным поиском",
                textAlign = TextAlign.Center
            )
        }
    }
}