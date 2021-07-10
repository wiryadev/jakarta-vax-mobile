package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import com.wiryadev.jakartavaxavailability.utils.capitalizeWords

@Composable
fun ItemRow(
    vaccineResponseItem: VaccineResponseItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier
                .background(
                    color = MaterialTheme.colors.background
                )
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Column {
                Text(
                    text = vaccineResponseItem.namaLokasiVaksinasi,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = "Diperbarui pada ${vaccineResponseItem.lastUpdatedAt}",
                    style = MaterialTheme.typography.body1.copy(
                        color = Color(0xFF4A5568)
                    )
                )
            }
            Column {
                Text(
                    text = "Kec/Kel: ${vaccineResponseItem.kecamatan.capitalizeWords()} / ${vaccineResponseItem.kelurahan.capitalizeWords()}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = vaccineResponseItem.wilayah.capitalizeWords(),
                    style = MaterialTheme.typography.body1
                )
            }
            LazyRow {
                items(vaccineResponseItem.jadwal) { jadwal ->
                    ItemDate(date = jadwal.id)
                }
            }
        }
    }
}

@Composable
fun ItemDate(
    date: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface
        ),
    ) {
        Text(
            text = date,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(
                    vertical = 2.dp,
                    horizontal = 8.dp
                )
        )
    }
}