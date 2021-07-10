package com.wiryadev.jakartavaxavailability.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.jakartavaxavailability.ui.components.ItemRow

@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val vaccines = viewModel.vaccines.value
    val loading = viewModel.loading.value

    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            if (loading && vaccines.isEmpty()) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(
                        items = vaccines
                    ) { index, vaccine ->
                        ItemRow(vaccineResponseItem = vaccine, onClick = {})
                    }
                }
            }
        }
    }
}