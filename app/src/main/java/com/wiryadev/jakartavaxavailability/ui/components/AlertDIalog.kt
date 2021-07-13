package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Koneksi Gagal") },
        text = { Text(text = "Pastikan jaringan kamu berjalan dengan baik ya") },
        confirmButton = {
            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 24.dp,
                ),
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
            ) {
                Text(
                    text = "Coba lagi",
                    style = MaterialTheme.typography.button,
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}