package com.codemockup.ecommercecommunity.features.navigation

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppNavigation {
    /// define reusable job for navigation
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main.immediate + job)

    /// store saved state
    private val _savedState = MutableStateFlow<SavedStateHandle?>(null)

    private val _currentDestination = MutableStateFlow<String?>(null)
    val currentDestination get() = _currentDestination

    // Internal channel for navigation events
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>(replay = 1)
    val navigationEvents = _navigationEvents.asSharedFlow()

    // Public function pass saved state from nav controller to class saved state var
    fun setSavedState(savedState: SavedStateHandle?) {
        _savedState.value = savedState
    }

    // Public method to set the current destination
    fun setCurrentDestination(destination: String?) {
        scope.launch {
            _currentDestination.emit(destination)
        }
    }

    // Public methods to emit navigation events
    fun navigateTo(route: String, launchSingleTop: Boolean = false) {
        scope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateToRoute(route, launchSingleTop))
        }
    }

    fun navigateWithPopUp(route: String, popUpToRoute: String, inclusive: Boolean = false) {
        scope.launch {
            _navigationEvents.emit(
                NavigationEvent.NavigateWithPopUp(
                    route,
                    popUpToRoute,
                    inclusive
                )
            )
        }
    }

    fun navigateAndClearBackstack(route: String) {
        scope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateAndClearBackstack(route))
        }
    }

    fun navigateWithArgs(
        route: String,
        args: String?,
        popUpToRoute: String? = null,
        inclusive: Boolean? = false
    ) {
        scope.launch {
            _navigationEvents.emit(
                NavigationEvent.NavigateWithArgs(
                    route,
                    args,
                    popUpToRoute,
                    inclusive
                )
            )
        }
    }

    fun navigateBack() {
        scope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateBack)
        }
    }

    /// save value to previous back stack based of string key and generic type
    fun <T> setSavedStatePreviousBackState(
        key: String,
        value: T,
    ) {
        scope.launch {
            _navigationEvents.emit(NavigationEvent.SetSavedStatePreviousBackState(key, value))
        }
    }

    /// synchronously fetch saved state
    fun <T> getSavedStateValue(key: String): T? {
        return _savedState.value?.get<T>(key)
    }

    /// clear all saved state from nav controller
    fun clearSavedState(key: String) {
        scope.launch {
            /// delete current local saved state value
            _savedState.value?.remove<Any>(key)
            /// delete saved state value on nav controller
            _navigationEvents.emit(NavigationEvent.ClearSavedState(key))
        }
    }

}