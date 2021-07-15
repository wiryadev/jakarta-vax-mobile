package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.ui.home.SearchType

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    enabled: Boolean,
    query: String,
    types: List<SearchType>,
    selectedType: SearchType,
    onQueryChanged: (String) -> Unit,
    onSelectedTypeChanged: (String) -> Unit,
    onBookmarkClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        BoxWithConstraints {
            val textFieldWidth = this.maxWidth - (48.dp + 8.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedTextField(
                    value = query,
                    enabled = enabled,
                    modifier = Modifier.width(textFieldWidth),
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    label = {
                        Text(
                            text = if (enabled) "Pencarian" else "Loading"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Cari ${selectedType.value}...",
                            style = MaterialTheme.typography.caption,
                        )
                    },
                    maxLines = 1,
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
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Icon",
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Search Icon",
                                modifier = Modifier.clickable {
                                    onQueryChanged("")
                                }
                            )
                        }
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface,
                    ),
                    shape = RoundedCornerShape(16.dp),
                )

                IconButton(
                    onClick = {
                        if (enabled) onBookmarkClicked()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CollectionsBookmark,
                        contentDescription = "Go To Bookmark Screen",
                        tint = if (enabled) LocalContentColor.current else {
                            LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                        },
                    )
                }
            }
        }


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
            .clip(RoundedCornerShape(50f))
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
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.SemiBold
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