package com.wiryadev.jakartavaxavailability.ui.detail

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import com.wiryadev.jakartavaxavailability.ui.components.*
import com.wiryadev.jakartavaxavailability.utils.capitalizeWords
import com.wiryadev.jakartavaxavailability.utils.returnDashIfNull

@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    locationName: String,
    viewModel: DetailViewModel,
    onNavigateUp: () -> Unit,
) {
    viewModel.locationName.value = locationName
    viewModel.getDetailItem()
    viewModel.checkBookmark()

    val loading by viewModel.loading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val result by viewModel.vaccineResponseItem.collectAsState()
    val schedules by viewModel.schedules.collectAsState()
    val selectedScheduleIndex by viewModel.selectedSchedules.collectAsState()
    val isBookmarked by viewModel.isBookmarked.collectAsState()

    Scaffold(
        topBar = {
            result?.let { item ->
                DetailAppBar(
                    result = item,
                    isBookmarked = isBookmarked,
                    onAddBookmark = { entity ->
                        viewModel.addToBookmark(entity)
                    },
                    onRemoveBookmark = { entity ->
                        viewModel.removeFromBookmark(entity)
                    },
                    onNavigateUp = onNavigateUp,
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
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
                    if (isError) {
                        ErrorScreen(
                            onRetryClick = { viewModel.refresh() },
                            modifier = Modifier.align(Alignment.Center),
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            result?.let {
                                item {
                                    LocationInfo(
                                        location = it,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .padding(top = 16.dp),
                                        onClick = {
                                            viewModel.goToMaps()
                                        }
                                    )
                                }
                            }

                            item {
                                ScheduleTabs(
                                    schedules = schedules,
                                    selectedSchedule = schedules[selectedScheduleIndex],
                                    onScheduleSelected = viewModel::setSelectedSchedule
                                )
                            }

                            item {
                                ScheduleTimeHeader()
                            }

                            val selectedSchedule = schedules[selectedScheduleIndex].waktu
                            items(selectedSchedule) { item ->
                                Crossfade(targetState = item) {
                                    ScheduleTimeItem(item = it)
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.navigationBarsHeight())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationInfo(
    location: VaccineResponseItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val faskes = location.jenisFaskes.returnDashIfNull()

    val alamat = location.alamatLokasiVaksinasi.returnDashIfNull()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = location.namaLokasiVaksinasi,
            style = MaterialTheme.typography.h5,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row {
                DetailProperty(
                    propertyName = "Kecamatan",
                    propertyValue = location.kecamatan.capitalizeWords(),
                    modifier = Modifier.weight(1f),
                )
                DetailProperty(
                    propertyName = "Kelurahan",
                    propertyValue = location.kelurahan.capitalizeWords(),
                    modifier = Modifier.weight(1f),
                )
            }
            DetailProperty(
                propertyName = "Wilayah",
                propertyValue = location.wilayah.capitalizeWords(),
            )
            DetailProperty(
                propertyName = "Jenis Faskes",
                propertyValue = faskes,
            )
            DetailProperty(
                propertyName = "Alamat",
                propertyValue = alamat,
            )
        }

        CustomOutlinedButton(
            onClick = onClick,
            text = "Lihat di Maps",
            leadingIcon = rememberVectorPainter(image = Icons.Rounded.LocationOn),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun DetailProperty(
    propertyName: String,
    propertyValue: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = propertyName,
            style = MaterialTheme.typography.body2,
        )
        Text(
            text = propertyValue,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}