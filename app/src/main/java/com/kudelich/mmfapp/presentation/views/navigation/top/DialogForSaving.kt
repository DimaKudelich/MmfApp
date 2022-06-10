package com.kudelich.mmfapp.presentation.views.navigation.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogForSaving(openDialog: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = { Text(text = "Подтверждение действия") },
        text = { Text("Вы действительно хотите удалить выбранный элемент?") },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { openDialog.value = false }
                ) {
                    Text("Удалить")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { openDialog.value = false }
                ) {
                    Text("Отмена")
                }
            }
        }
    )
}