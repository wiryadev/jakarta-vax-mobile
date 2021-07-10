package com.wiryadev.jakartavaxavailability.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.wiryadev.jakartavaxavailability.utils.compareToCurrentDateTime
import com.wiryadev.jakartavaxavailability.utils.getMinutes
import com.wiryadev.jakartavaxavailability.utils.toDate

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
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.background
                )
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Column {
                val formattedDate = vaccineResponseItem.lastUpdatedAt.toDate()
                val lastUpdated = formattedDate?.compareToCurrentDateTime()?.getMinutes()

                Text(
                    text = vaccineResponseItem.namaLokasiVaksinasi,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = "Diperbarui $lastUpdated menit lalu",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSecondary
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
                    vertical = 4.dp,
                    horizontal = 12.dp
                )
        )
    }
}