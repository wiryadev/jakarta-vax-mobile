package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.remote.response.Jadwal

private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
fun ScheduleTabs(
    schedules: List<Jadwal>,
    selectedSchedule: Jadwal,
    onScheduleSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = schedules.indexOfFirst { it == selectedSchedule }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 16.dp,
        indicator = emptyTabIndicator,
        divider = { }, /* Disable the built-in divider */
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier,
    ) {
        schedules.forEachIndexed { index, item ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onScheduleSelected(index) },
            ) {
                ScheduleChip(
                    text = item.id,
                    isSelected = index == selectedIndex,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun ScheduleChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50f))
            .background(
                when {
                    isSelected -> MaterialTheme.colors.primary.copy(alpha = 0.15f)
                    else -> MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                }
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = when {
                isSelected -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.onSurface
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}