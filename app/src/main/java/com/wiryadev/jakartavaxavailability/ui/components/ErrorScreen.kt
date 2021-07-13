package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.R

@Composable
fun ErrorScreen(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IllustrationWithText(
            imageId = R.drawable.ic_signal_searching,
            title = "Koneksi gagal",
            message = " Pastikan anda terhubung dengan jaringan yang stabil",
        )
        CustomButton(
            onClick = onRetryClick,
            text = "Coba Lagi",
        )
    }
}