package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import com.wiryadev.jakartavaxavailability.utils.*

@Composable
fun ItemVaccineAvailability(
    vaccineResponseItem: VaccineResponseItem,
    onClick: (String) -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface
            )
            .clickable(
                onClick = {
                    if (!loading) {
                        onClick(vaccineResponseItem.namaLokasiVaksinasi)
                    }
                }
            ),
    ) {
        Divider()
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column {
                val formattedDate = vaccineResponseItem.lastUpdatedAt.toDate()
                val lastUpdated = formattedDate?.compareToCurrentDateTime()?.getMinutes()

                Text(
                    text = vaccineResponseItem.namaLokasiVaksinasi,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.shimmerPlaceholder(visible = loading),
                )
                Text(
                    text = "Diperbarui $lastUpdated menit lalu",
                    style = MaterialTheme.typography.subtitle2.copy(
                        color = MaterialTheme.colors.onSecondary
                    ),
                    modifier = Modifier.shimmerPlaceholder(visible = loading),
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = "Kec / Kel: ${vaccineResponseItem.kecamatan.capitalizeWords()} / ${vaccineResponseItem.kelurahan.capitalizeWords()}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.shimmerPlaceholder(visible = loading),
                )
                Text(
                    text = vaccineResponseItem.wilayah.capitalizeWords(),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.shimmerPlaceholder(visible = loading),
                )
            }
            LazyRow {
                itemsIndexed(vaccineResponseItem.jadwal) { index, jadwal ->
                    ItemDate(date = jadwal.id, index = index, loading = loading)
                }
            }
        }
    }
}

@Composable
fun ItemDate(
    date: String,
    index: Int,
    loading: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(
                top = 8.dp,
                bottom = 8.dp,
                start = if (index == 0) 0.dp else 8.dp
            )
            .shimmerPlaceholder(visible = loading),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface
        ),
    ) {
        Text(
            text = date,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.9f),
            style = MaterialTheme.typography.overline,
            modifier = Modifier
                .padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                )
        )
    }
}