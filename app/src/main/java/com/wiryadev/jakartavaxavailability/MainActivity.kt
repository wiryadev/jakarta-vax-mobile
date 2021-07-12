package com.wiryadev.jakartavaxavailability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wiryadev.jakartavaxavailability.ui.home.HomeScreen
import com.wiryadev.jakartavaxavailability.ui.home.HomeViewModel
import com.wiryadev.jakartavaxavailability.ui.theme.JakartaVaxAvailabilityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JakartaVaxAvailabilityTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    AppNavGraph()
                }
            }
        }
    }
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