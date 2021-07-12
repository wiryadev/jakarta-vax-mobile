package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem

@Composable
fun VerticalList(
    items: List<VaccineResponseItem>,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        itemsIndexed(items = items) { index, item ->
            ItemRow(vaccineResponseItem = item, onClick = onItemClick)

            if (index == (items.size-1)) {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}