package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun BackButton(onNavigateUp: () -> Unit) {
    IconButton(
        onClick = onNavigateUp,
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Kembali"
        )
    }
}