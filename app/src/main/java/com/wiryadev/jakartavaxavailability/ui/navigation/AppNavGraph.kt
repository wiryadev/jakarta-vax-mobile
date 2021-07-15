package com.wiryadev.jakartavaxavailability.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.wiryadev.jakartavaxavailability.ui.bookmark.BookmarkScreen
import com.wiryadev.jakartavaxavailability.ui.bookmark.BookmarkViewModel
import com.wiryadev.jakartavaxavailability.ui.detail.DetailScreen
import com.wiryadev.jakartavaxavailability.ui.detail.DetailViewModel
import com.wiryadev.jakartavaxavailability.ui.home.HomeScreen
import com.wiryadev.jakartavaxavailability.ui.home.HomeViewModel

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainNavigation.HOME_ROUTE
) {
    var detailScreenIsVisible by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = MainNavigation.HOME_ROUTE) { navBackStackEntry ->
            detailScreenIsVisible = false
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                viewModel = viewModel,
                onNavigationBookmark = {
                    if (navBackStackEntry.lifecycleIsResumed()) {
                        navController.navigate(MainNavigation.BOOKMARK_ROUTE)
                    }
                },
                onNavigationDetail = { locationId ->
                    if (navBackStackEntry.lifecycleIsResumed()) {
                        navController.navigate("${MainNavigation.DETAIL_ROUTE}/${locationId}")
                        detailScreenIsVisible = true
                    }
                }
            )
        }

        composable(route = MainNavigation.BOOKMARK_ROUTE) { navBackStackEntry ->
            detailScreenIsVisible = false
            val viewModel = hiltViewModel<BookmarkViewModel>()

            AnimatedVisibility(
                visibleState = remember { MutableTransitionState(false) }
                    .apply { targetState = true },
                enter = fadeIn() + expandIn(
                    expandFrom = Alignment.BottomStart,
                ),
            ) {
                BookmarkScreen(
                    viewModel = viewModel,
                    onNavigationDetail = { locationId ->
                        if (navBackStackEntry.lifecycleIsResumed()) {
                            navController.navigate("${MainNavigation.DETAIL_ROUTE}/${locationId}")
                            detailScreenIsVisible = true
                        }
                    },
                    onNavigateUp = {
                        navController.navigateUp()
                    }
                )
            }
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

            AnimatedVisibility(
                visibleState = remember { MutableTransitionState(!detailScreenIsVisible) }
                    .apply { targetState = detailScreenIsVisible },
                enter = slideInVertically(
                    initialOffsetY = { it / 3 }
                ) + fadeIn(),
            ) {
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

}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED