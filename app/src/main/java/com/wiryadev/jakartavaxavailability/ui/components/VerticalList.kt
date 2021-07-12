package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem

@Composable
fun VerticalList(
    items: List<VaccineResponseItem>,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items(
            items = items,
        ) { item ->
            ItemRow(vaccineResponseItem = item, onClick = onItemClick)
        }
    }
}