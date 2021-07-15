package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import com.wiryadev.jakartavaxavailability.utils.capitalizeWords

@Composable
fun ItemBookmark(
    entity: VaccineBookmarkEntity,
    onClick: (String) -> Unit,
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
                    onClick(entity.namaLokasiVaksinasi)
                }
            ),
    ) {
        Divider()
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = entity.namaLokasiVaksinasi,
                style = MaterialTheme.typography.h6,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = "Kecamatan: ${entity.kecamatan.capitalizeWords()}",
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    text = "Kelurahan: ${entity.kelurahan.capitalizeWords()}",
                    style = MaterialTheme.typography.body1,
                )
            }
            Text(
                text = entity.wilayah.capitalizeWords(),
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }
}