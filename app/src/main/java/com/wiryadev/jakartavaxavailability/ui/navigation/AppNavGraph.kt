package com.wiryadev.jakartavaxavailability.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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

        composable(
            route = MainNavigation.HOME_ROUTE,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    MainNavigation.DETAIL_ROUTE -> {
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.DETAIL_ROUTE -> {
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    MainNavigation.DETAIL_ROUTE -> {
                        slideInHorizontally(
                            initialOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                    else -> null
                }
            },
        ) { navBackStackEntry ->
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
                            animationSpec = tween(300)
                        )
                    }
                    MainNavigation.DETAIL_ROUTE -> {
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.HOME_ROUTE -> {
                        fadeOut() + shrinkOut(
                            shrinkTowards = Alignment.BottomStart,
                            animationSpec = tween(300)
                        )
                    }
                    MainNavigation.DETAIL_ROUTE -> {
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    MainNavigation.HOME_ROUTE -> {
                        fadeIn() + expandIn(
                            expandFrom = Alignment.BottomStart,
                            animationSpec = tween(300)
                        )
                    }
                    MainNavigation.DETAIL_ROUTE -> {
                        slideInHorizontally(
                            initialOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                    else -> null
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.HOME_ROUTE -> {
                        fadeOut() + shrinkOut(
                            shrinkTowards = Alignment.BottomStart,
                            animationSpec = tween(300)
                        )
                    }
                    MainNavigation.DETAIL_ROUTE -> {
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
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

        val detailEnterTransition = slideInHorizontally(
            initialOffsetX = { 300 },
            animationSpec = tween(300)
        ) + fadeIn(animationSpec = tween(300))

        val detailExitTransition = slideOutHorizontally(
            targetOffsetX = { -300 }, animationSpec = tween(300)
        ) + fadeOut(animationSpec = tween(300))

        val detailPopExitTransition = slideOutHorizontally(
            targetOffsetX = { 300 },
            animationSpec = tween(300)
        ) + fadeOut(animationSpec = tween(300))

        composable(
            route = "${MainNavigation.DETAIL_ROUTE}/{${MainNavigation.ArgsKey.DETAIL_LOCATION_ID}}",
            arguments = listOf(
                navArgument(
                    name = MainNavigation.ArgsKey.DETAIL_LOCATION_ID,
                    builder = { type = NavType.StringType }
                )
            ),
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    MainNavigation.HOME_ROUTE -> detailEnterTransition
                    MainNavigation.BOOKMARK_ROUTE -> detailEnterTransition
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.HOME_ROUTE -> detailExitTransition
                    MainNavigation.BOOKMARK_ROUTE -> detailExitTransition
                    else -> null
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    MainNavigation.HOME_ROUTE -> detailPopExitTransition
                    MainNavigation.BOOKMARK_ROUTE -> detailPopExitTransition
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