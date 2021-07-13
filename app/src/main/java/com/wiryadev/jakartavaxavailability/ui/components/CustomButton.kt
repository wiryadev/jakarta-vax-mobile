package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 36.dp,
        ),
        modifier = modifier,
    ) {
        Text(
            text = text
        )
    }
}