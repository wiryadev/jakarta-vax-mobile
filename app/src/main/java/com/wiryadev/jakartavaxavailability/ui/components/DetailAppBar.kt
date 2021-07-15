package com.wiryadev.jakartavaxavailability.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.rounded.BookmarkAdded
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem

@ExperimentalAnimationApi
@Composable
fun DetailAppBar(
    result: VaccineResponseItem,
    isBookmarked: Boolean,
    onAddBookmark: (VaccineBookmarkEntity) -> Unit,
    onRemoveBookmark: (VaccineBookmarkEntity) -> Unit,
    onNavigateUp: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        BackButton(
            onNavigateUp = onNavigateUp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Detail Lokasi Vaksinasi")
            IconButton(
                onClick = {
                    val bookmarkEntity = VaccineBookmarkEntity(
                        namaLokasiVaksinasi = result.namaLokasiVaksinasi,
                        kecamatan = result.kecamatan,
                        kelurahan = result.kelurahan,
                        wilayah = result.kelurahan
                    )

                    if (isBookmarked) {
                        onRemoveBookmark(bookmarkEntity)
                    } else {
                        onAddBookmark(bookmarkEntity)
                    }

                },
            ) {
                AnimatedVisibility(
                    visibleState = remember { MutableTransitionState(!isBookmarked) }
                        .apply { targetState = isBookmarked },
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically(
                        targetOffsetY = { it }
                    ) + fadeOut(),
                ) {
                    Icon(
                        Icons.Rounded.BookmarkAdded,
                        contentDescription = "Bookmark Button"
                    )
                }
                AnimatedVisibility(
                    visibleState = remember { MutableTransitionState(isBookmarked) }
                        .apply { targetState = !isBookmarked },
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically(
                        targetOffsetY = { it }
                    ) + fadeOut(),
                ) {
                    Icon(
                        Icons.Outlined.BookmarkAdd,
                        contentDescription = "Bookmark Button"
                    )
                }
            }
        }
    }
}