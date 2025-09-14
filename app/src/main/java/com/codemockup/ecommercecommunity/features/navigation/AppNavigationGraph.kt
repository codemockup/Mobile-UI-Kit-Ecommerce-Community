package com.codemockup.ecommercecommunity.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codemockup.ecommercecommunity.features.screens.home.views.pages.HomePage
import com.codemockup.ecommercecommunity.utils.helper.NavigationHelper
import org.koin.compose.koinInject

@Composable
fun AppNavigationGraph(
    appNavigation: AppNavigation = koinInject(),
    navController: NavHostController,
) {
    val navControllerDestination by navController.currentBackStackEntryAsState()

    /**
     * Listen for navigation events and update the current destination
     */
    LaunchedEffect(navControllerDestination) {
        val destination = navControllerDestination?.destination?.route
        appNavigation.setCurrentDestination(destination)

        /// pass saved state to app navigation savedState var to be access using getSavedStateValue
        appNavigation.setSavedState(navController.currentBackStackEntry?.savedStateHandle)
    }

    /// listen for navigation events
    LaunchedEffect(Unit) {
        appNavigation.navigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.NavigateAndClearBackstack -> {
                    navController.navigate(event.route) {
                        popUpTo(0)
                    }
                }

                is NavigationEvent.NavigateBack -> {
                    navController.popBackStack()
                }

                is NavigationEvent.NavigateToRoute -> {
                    navController.navigate(event.route)
                }

                is NavigationEvent.NavigateWithArgs -> {
                    val route = NavigationHelper.createRouteWithArgs(event.route, event.args)
                    navController.navigate(route)
                }

                is NavigationEvent.NavigateWithPopUp -> {
                    navController.navigate(event.route) {
                        popUpTo(event.popUpToRoute) {
                            inclusive = event.inclusive
                        }
                    }
                }

                /// save value to previous back stack
                is NavigationEvent.SetSavedStatePreviousBackState<*> -> {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        event.key,
                        event.value
                    )
                }

                /// clear current saved state
                is NavigationEvent.ClearSavedState -> {
                    navController.currentBackStackEntry?.savedStateHandle?.remove<Any>(event.key)
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            HomePage()
        }
    }
}