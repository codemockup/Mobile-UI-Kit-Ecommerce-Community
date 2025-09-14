package com.codemockup.ecommercecommunity.features.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.screens.home.repositories.HomeRepository
import com.codemockup.ecommercecommunity.features.screens.home.states.HomeStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val appNavigation: AppNavigation,
    private val repository: HomeRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeStates())
    val state get() = _state

    fun setErrorShown(isShown: Boolean) {
        _state.update {
            it.copy(hasShownError = isShown)
        }
    }

}