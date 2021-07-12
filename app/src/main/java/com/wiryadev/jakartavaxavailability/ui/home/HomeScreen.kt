package com.wiryadev.jakartavaxavailability.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.wiryadev.jakartavaxavailability.data.getSearchTypes
import com.wiryadev.jakartavaxavailability.ui.components.ItemRow
import com.wiryadev.jakartavaxavailability.ui.components.SearchAppBar
import com.wiryadev.jakartavaxavailability.ui.components.VerticalList

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val query = viewModel.query.value
    val selectedType = viewModel.searchType.value

    val loading by viewModel.loading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val result by viewModel.result.collectAsState()
//    val searchResult by viewModel.searchResult.collectAsState()

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
                    VerticalList(items = result, onItemClick = {})
                }
            }
        }
    }
}