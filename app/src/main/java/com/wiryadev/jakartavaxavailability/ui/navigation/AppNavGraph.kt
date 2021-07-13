package com.wiryadev.jakartavaxavailability.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.wiryadev.jakartavaxavailability.ui.detail.DetailScreen
import com.wiryadev.jakartavaxavailability.ui.detail.DetailViewModel
import com.wiryadev.jakartavaxavailability.ui.home.HomeScreen
import com.wiryadev.jakartavaxavailability.ui.home.HomeViewModel

@ExperimentalComposeUiApi
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainNavigation.HOME_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = MainNavigation.HOME_ROUTE) { navBackStackEntry ->
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                onNavigationEvent = { locationId ->
                    if (navBackStackEntry.lifecycleIsResumed()) {
                        navController.navigate("${MainNavigation.DETAIL_ROUTE}/${locationId}")
                    }
                }
            )
        }

        composable(
            route = "${MainNavigation.DETAIL_ROUTE}/{${MainNavigation.ArgsKey.DETAIL_LOCATION_ID}}",
            arguments = listOf(
                navArgument(
                    name = MainNavigation.ArgsKey.DETAIL_LOCATION_ID,
                    builder = {
                        type = NavType.StringType
                    }
                )
            )
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val locationName =
                requireNotNull(arguments.getString(MainNavigation.ArgsKey.DETAIL_LOCATION_ID))

            val viewModel = hiltViewModel<DetailViewModel>()

            DetailScreen(
                locationName = locationName,
                viewModel = viewModel,
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED