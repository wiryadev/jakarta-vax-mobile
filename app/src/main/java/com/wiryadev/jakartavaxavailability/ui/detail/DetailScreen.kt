package com.wiryadev.jakartavaxavailability.ui.detail

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.data.response.Jadwal
import com.wiryadev.jakartavaxavailability.ui.components.ScheduleTabs

@Composable
fun DetailScreen(
    locationName: String,
    viewModel: DetailViewModel,
    onNavigateUp: () -> Unit,
) {
    viewModel.locationName.value = locationName
    viewModel.getDetailItem()

    val loading by viewModel.loading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val result by viewModel.vaccineResponseItem.collectAsState()
    val schedules by viewModel.schedules.collectAsState()
    val selectedScheduleIndex by viewModel.selectedSchedules.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (schedules.isNotEmpty()) {
            Schedule(
                schedules = schedules,
                selectedSchedule = schedules[selectedScheduleIndex],
                onScheduleSelected = viewModel::setSelectedSchedule
            )
            Log.d("Detail", "schedule: ${schedules[0]}")
        }
        Log.d("Detail", "locationId: ${result?.namaLokasiVaksinasi}")
    }
}

@Composable
fun Schedule(
    schedules: List<Jadwal>,
    selectedSchedule: Jadwal,
    onScheduleSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Spacer(Modifier.height(8.dp))

        ScheduleTabs(
            schedules = schedules,
            selectedSchedule = selectedSchedule,
            onScheduleSelected = onScheduleSelected,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

//        Crossfade(
//            targetState = selectedCategory,
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        ) { category ->
//
//            PodcastCategory(
//                categoryId = category.id,
//                modifier = Modifier
//                    .fillMaxSize()
//            )
//        }
    }
}