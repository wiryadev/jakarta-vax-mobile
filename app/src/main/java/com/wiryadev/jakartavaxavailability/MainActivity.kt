package com.wiryadev.jakartavaxavailability

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.wiryadev.jakartavaxavailability.ui.home.HomeScreen
import com.wiryadev.jakartavaxavailability.ui.home.HomeViewModel
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
                    val context = LocalContext.current
                    var isOnline by remember { mutableStateOf(checkIfOnline(context)) }

                    if (isOnline) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            AppNavGraph()
                        }
                    } else {
                        OfflineDialog { isOnline = checkIfOnline(context) }
                    }
                }
            }
        }
    }
}

@Suppress("DEPRECATION")
private fun checkIfOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Koneksi Gagal") },
        text = { Text(text = "Pastikan jaringan kamu berjalan dengan baik ya") },
        confirmButton = {
            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 24.dp,
                ),
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
            ) {
                Text(
                    text = "Coba lagi",
                    style = MaterialTheme.typography.h6,
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}

@ExperimentalComposeUiApi
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = MainDestinations.HOME_ROUTE) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = viewModel)
        }
    }
}

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "vaccine"
    const val ID_KEY = "vaccineId"
}