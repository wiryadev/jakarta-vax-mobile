package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.navigationBarsHeight
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem

@Composable
fun VaccineAvailabilityList(
    items: List<VaccineResponseItem>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(items = items) { index, item ->
            ItemVaccineAvailability(vaccineResponseItem = item, onClick = onItemClick)

            if (index == (items.size - 1)) {
                Spacer(
                    modifier = Modifier.navigationBarsHeight()
                )
            }
        }
    }
}

@Composable
fun BookmarkList(
    items: List<VaccineBookmarkEntity>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(items = items) { index, item ->
            ItemBookmark(entity = item, onClick = onItemClick)

            if (index == (items.size - 1)) {
                Spacer(
                    modifier = Modifier.navigationBarsHeight()
                )
            }
        }
    }
}