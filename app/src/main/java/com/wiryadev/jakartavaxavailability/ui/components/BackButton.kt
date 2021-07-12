package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsPadding

@Composable
private fun BackButton(onNavigateUp: () -> Unit) {
    IconButton(
        onClick = onNavigateUp,
        modifier = Modifier.statusBarsPadding()
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Kembali"
        )
    }
}