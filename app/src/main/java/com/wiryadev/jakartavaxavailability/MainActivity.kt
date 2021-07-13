package com.wiryadev.jakartavaxavailability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.wiryadev.jakartavaxavailability.ui.navigation.AppNavGraph
import com.wiryadev.jakartavaxavailability.ui.theme.JakartaVaxAvailabilityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JakartaVaxAvailabilityTheme {
                ProvideWindowInsets {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Column {
                            val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)

                            Spacer(
                                modifier = Modifier
                                    .background(appBarColor)
                                    .fillMaxWidth()
                                    .statusBarsHeight()
                            )

                            AppNavGraph()
                        }
                    }
                }
            }
        }
    }
}