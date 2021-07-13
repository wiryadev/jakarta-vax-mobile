package com.wiryadev.jakartavaxavailability.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.wiryadev.jakartavaxavailability.R
import com.wiryadev.jakartavaxavailability.data.getSearchTypes
import com.wiryadev.jakartavaxavailability.ui.components.CustomButton
import com.wiryadev.jakartavaxavailability.ui.components.IllustrationWithText
import com.wiryadev.jakartavaxavailability.ui.components.SearchAppBar
import com.wiryadev.jakartavaxavailability.ui.components.VaccineAvailabilityList

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigationEvent: (String) -> Unit,
) {
    val query = viewModel.query.value
    val selectedType = viewModel.searchType.value

    val loading by viewModel.loading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val result by viewModel.result.collectAsState()

    Scaffold(
        topBar = {
            SearchAppBar(
                enabled = !(loading || isRefreshing),
                query = query,
                types = getSearchTypes(),
                selectedType = selectedType,
                onQueryChanged = {
                    viewModel.onQueryChanged(it)
                },
                onSelectedTypeChanged = {
                    viewModel.onSelectedTypeChanged(it)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            if (loading && result.isEmpty()) {
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                                .align(Alignment.Center),
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            IllustrationWithText(
                                imageId = R.drawable.ic_signal_searching,
                                title = "Koneksi gagal",
                                message = " Pastikan anda terhubung dengan jaringan yang stabil",
                            )
                            CustomButton(
                                onClick = { viewModel.refresh() },
                                text = "Coba Lagi",
                            )
                        }
                    } else if (result.isEmpty() && !isError) {
                        IllustrationWithText(
                            imageId = R.drawable.ic_empty_result,
                            title = "Data tidak ditemukan",
                            message = "Cobalah dengan kata kunci lain",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        VaccineAvailabilityList(items = result, onItemClick = onNavigationEvent)
                    }
                }
            }
        }
    }
}