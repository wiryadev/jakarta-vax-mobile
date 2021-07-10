package com.wiryadev.jakartavaxavailability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wiryadev.jakartavaxavailability.ui.home.HomeScreen
import com.wiryadev.jakartavaxavailability.ui.home.MainViewModel
import com.wiryadev.jakartavaxavailability.ui.theme.JakartaVaxAvailabilityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JakartaVaxAvailabilityTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    AppNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavGraph(
    viewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = MainDestinations.HOME_ROUTE) {
            HomeScreen(viewModel = viewModel)
        }
    }
}

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "vaccine"
    const val ID_KEY = "vaccineId"
}