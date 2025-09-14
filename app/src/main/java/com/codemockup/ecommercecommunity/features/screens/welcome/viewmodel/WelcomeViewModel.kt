package com.codemockup.ecommercecommunity.features.screens.welcome.viewmodel

import androidx.lifecycle.ViewModel
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.navigation.Screen
import com.codemockup.ecommercecommunity.features.screens.welcome.repositories.WelcomeRepository
import com.codemockup.ecommercecommunity.features.screens.welcome.states.WelcomeStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class WelcomeViewModel(
    private val appNavigation: AppNavigation,
    private val repository: WelcomeRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeStates())
    val state get() = _state

    fun setErrorShown(isShown: Boolean) {
        _state.update {
            it.copy(hasShownError = isShown)
        }
    }

    fun navigateToHome() {
        appNavigation.navigateAndClearBackstack(Screen.Home.route)
    }
}