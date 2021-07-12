package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiryadev.jakartavaxavailability.data.SearchType

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    enabled: Boolean,
    query: String,
    types: List<SearchType>,
    selectedType: SearchType,
    onQueryChanged: (String) -> Unit,
    onSelectedTypeChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = query,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                onQueryChanged(it)
            },
            label = {
                Text(text = if (enabled) "Pencarian" else "Loading")
            },
            placeholder = {
                Text(
                    text = "Cari kecamatan/kelurahan/nama lokasi vaksinasi",
                    style = MaterialTheme.typography.caption,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                },
            ),
            leadingIcon = {
                Icon(Icons.Rounded.Search, contentDescription = "Search Icon")
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
        )

        BoxWithConstraints {
            val width = (this.maxWidth - ((2 * 16).dp)) / 3

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                for (item in types) {
                    SearchTypeChip(
                        type = item.value,
                        isSelected = selectedType == item,
                        width = width,
                        onSelectedTypeChanged = {
                            onSelectedTypeChanged(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchTypeChip(
    type: String,
    isSelected: Boolean,
    width: Dp,
    onSelectedTypeChanged: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .width(width = width)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when {
                    isSelected -> MaterialTheme.colors.primary.copy(alpha = 0.15f)
                    else -> Color.Transparent
                }
            )
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedTypeChanged(type)
                }
            ),
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.h6.copy(
                fontSize = 12.sp
            ),
            color = when {
                isSelected -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.onBackground
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.Center),
        )
    }
}