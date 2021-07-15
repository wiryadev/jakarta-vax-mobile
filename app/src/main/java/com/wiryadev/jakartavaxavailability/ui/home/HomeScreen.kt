package com.wiryadev.jakartavaxavailability.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.wiryadev.jakartavaxavailability.R
import com.wiryadev.jakartavaxavailability.ui.components.ErrorScreen
import com.wiryadev.jakartavaxavailability.ui.components.IllustrationWithText
import com.wiryadev.jakartavaxavailability.ui.components.SearchAppBar
import com.wiryadev.jakartavaxavailability.ui.components.VaccineAvailabilityList
import com.wiryadev.jakartavaxavailability.utils.DummyPlaceholder

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigationBookmark: () -> Unit,
    onNavigationDetail: (String) -> Unit,
) {
    val query = viewModel.query.value
    val selectedType = viewModel.searchType.value

    val loading by viewModel.loading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val results by viewModel.result.collectAsState()

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
                },
                onBookmarkClicked = onNavigationBookmark,
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
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
                } else if (loading || isRefreshing || results.isNotEmpty()) {
                    VaccineAvailabilityList(
                        items = if (results.isNotEmpty()) {
                            results
                        } else {
                               DummyPlaceholder.getDummyList()
                        },
                        onItemClick = onNavigationDetail,
                        loading = loading || isRefreshing,
                    )
                } else {
                    IllustrationWithText(
                        imageId = R.drawable.ic_empty_result,
                        title = "Data tidak ditemukan",
                        message = "Cobalah dengan kata kunci lain",
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}