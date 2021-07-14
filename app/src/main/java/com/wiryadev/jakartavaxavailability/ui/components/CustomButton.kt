package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
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
        if (leadingIcon != null) {
            Icon(
                painter = leadingIcon,
                contentDescription = text,
                modifier = Modifier.size(20.dp),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text
        )
    }
}

@Composable
fun CustomOutlinedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 36.dp,
        ),
        modifier = modifier,
    ) {
        if (leadingIcon != null) {
            Icon(
                painter = leadingIcon,
                contentDescription = text,
                modifier = Modifier.size(20.dp),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text
        )
    }
}