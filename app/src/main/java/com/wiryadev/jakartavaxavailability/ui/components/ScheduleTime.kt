package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.remote.response.Waktu
import com.wiryadev.jakartavaxavailability.utils.returnDashIfNull

@Composable
fun ScheduleTimeHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "WAKTU",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "SISA KUOTA",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "TOTAL KUOTA",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f),
            )
        }
        Divider()
    }
}

@Composable
fun ScheduleTimeItem(
    item: Waktu,
) {
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = item.id,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f),
            )
            Text(
                text = item.kuota?.sisaKuota.toString().returnDashIfNull(),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f),
            )
            Text(
                text = item.kuota?.totalKuota.returnDashIfNull(),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f),
            )
        }
        Divider()
    }
}