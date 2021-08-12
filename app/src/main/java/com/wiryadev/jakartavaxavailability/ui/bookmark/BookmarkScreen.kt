package com.wiryadev.jakartavaxavailability.ui.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wiryadev.jakartavaxavailability.R
import com.wiryadev.jakartavaxavailability.ui.components.BackButton
import com.wiryadev.jakartavaxavailability.ui.components.BookmarkList
import com.wiryadev.jakartavaxavailability.ui.components.IllustrationWithText

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel,
    onNavigationDetail: (String) -> Unit,
    onBackPressed: () -> Unit,
) {
    val list by viewModel.bookmarkList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                BackButton(onNavigateUp = onBackPressed)
                Text(text = "Daftar Bookmark")
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
        ) {
            if (list.isEmpty()) {
                IllustrationWithText(
                    imageId = R.drawable.ic_bookmark_page,
                    title = "Bookmark Kosong",
                    message = "Belum ada lokasi vaksinasi yang ditambahkan ke bookmark",
                    modifier = Modifier.align(Alignment.Center),
                )
            } else {
                BookmarkList(
                    items = list,
                    onItemClick = onNavigationDetail,
                )
            }
        }
    }
}