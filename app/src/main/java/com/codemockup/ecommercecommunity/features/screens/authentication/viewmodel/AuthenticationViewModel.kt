package com.codemockup.ecommercecommunity.features.screens.authentication.viewmodel

import androidx.lifecycle.ViewModel
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.screens.authentication.repositories.AuthenticationRepository
import com.codemockup.ecommercecommunity.features.screens.authentication.states.AuthenticationStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationViewModel(
    private val appNavigation: AppNavigation,
    private val repository: AuthenticationRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthenticationStates())
    val state get() = _state

    fun setErrorShown(isShown: Boolean) {
        _state.update {
            it.copy(hasShownError = isShown)
        }
    }

    fun updateName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

}