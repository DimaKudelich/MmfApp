package com.kudelich.mmfapp.presentation.views.status

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StatusBlock(
    isLoading: Boolean,
    errorMessage: String,
    isDataEmpty: Boolean,
    type: String
) {
    when (type) {
        "Center" -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (isLoading && isDataEmpty) {
                    CircularProgressIndicator()
                }
                if (!isLoading && isDataEmpty && errorMessage.isNotBlank()) {
                    Text(text = errorMessage, textAlign = TextAlign.Center,modifier = Modifier.padding(10.dp))
                }
            }
        }
        "Top" -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (isLoading && !isDataEmpty) {
                    CircularProgressIndicator()
                }
                if (!isLoading && !isDataEmpty && errorMessage.isNotBlank()) {
                    Text(text = errorMessage, textAlign = TextAlign.Center)
                }
            }
        }
    }

}