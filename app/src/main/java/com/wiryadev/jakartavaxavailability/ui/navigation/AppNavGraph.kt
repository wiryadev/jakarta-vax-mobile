package com.wiryadev.jakartavaxavailability.ui.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
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
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = MainNavigation.HOME_ROUTE
) {
    AnimatedNavHost(navController = navController, startDestination = startDestination) {

        composable(route = MainNavigation.HOME_ROUTE) { navBackStackEntry ->
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
                    }
                }
            )
        }

        composable(
            route = MainNavigation.BOOKMARK_ROUTE,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    MainNavigation.HOME_ROUTE -> {
                        fadeIn() + expandIn(
                            expandFrom = Alignment.BottomStart,
                        )
                    }
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.HOME_ROUTE ->
                        fadeOut() + shrinkOut(
                            shrinkTowards = Alignment.BottomStart,
                        )
                    else -> null
                }
            },
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<BookmarkViewModel>()

            BookmarkScreen(
                viewModel = viewModel,
                onNavigationDetail = { locationId ->
                    if (navBackStackEntry.lifecycleIsResumed()) {
                        navController.navigate("${MainNavigation.DETAIL_ROUTE}/${locationId}")
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
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
            ),
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    MainNavigation.HOME_ROUTE -> {
                        slideInVertically(
                            initialOffsetY = { it / 3 }
                        ) + fadeIn()
                    }
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.HOME_ROUTE ->
                        slideOutVertically(
                            targetOffsetY = { it / 3 }
                        ) + fadeOut()
                    else -> null
                }
            },
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