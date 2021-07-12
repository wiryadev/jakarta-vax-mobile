package com.wiryadev.jakartavaxavailability.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.wiryadev.jakartavaxavailability.data.response.Jadwal
import com.wiryadev.jakartavaxavailability.ui.components.ScheduleTabs
import com.wiryadev.jakartavaxavailability.ui.components.ScheduleTimeHeader
import com.wiryadev.jakartavaxavailability.ui.components.ScheduleTimeItem

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

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            if (loading && schedules.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = { viewModel.refresh() },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            Schedule(
                                schedules = schedules,
                                selectedSchedule = schedules[selectedScheduleIndex],
                                onScheduleSelected = viewModel::setSelectedSchedule
                            )
                        }
                        item {
                            ScheduleTimeHeader()
                        }

                        items(schedules[selectedScheduleIndex].waktu) { item ->
                            ScheduleTimeItem(item = item)
                        }
                    }
                }
            }
        }
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
        ScheduleTabs(
            schedules = schedules,
            selectedSchedule = selectedSchedule,
            onScheduleSelected = onScheduleSelected,
            modifier = Modifier.fillMaxWidth()
        )

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