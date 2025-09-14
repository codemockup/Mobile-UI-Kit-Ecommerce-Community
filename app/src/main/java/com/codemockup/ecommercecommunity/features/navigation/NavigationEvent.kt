package com.codemockup.ecommercecommunity.features.navigation

sealed class NavigationEvent {
    // Simple navigation with no arguments
    data class NavigateToRoute(val route: String, val launchSingleTop: Boolean) : NavigationEvent()

    // Navigation with arguments
    data class NavigateWithArgs(
        val route: String,
        val args: String?,
        val popUpToRoute: String? = null,
        val inclusive: Boolean? = false
    ) :
        NavigationEvent()

    // Navigation with popUpTo behavior
    data class NavigateWithPopUp(
        val route: String,
        val popUpToRoute: String,
        val inclusive: Boolean = false
    ) : NavigationEvent()

    // Clear backstack and navigate
    data class NavigateAndClearBackstack(val route: String) : NavigationEvent()

    // Navigate back
    data object NavigateBack : NavigationEvent()

    // Save value to previous back state event
    data class SetSavedStatePreviousBackState<T>(
        val key: String,
        val value: T
    ) : NavigationEvent()

    /// clear current saved state
    data class ClearSavedState(val key: String) : NavigationEvent()

}