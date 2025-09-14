package com.codemockup.ecommercecommunity.features.screens.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.navigation.Screen
import com.codemockup.ecommercecommunity.features.screens.authentication.repositories.AuthenticationRepository
import com.codemockup.ecommercecommunity.features.screens.authentication.states.AuthenticationStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    fun updateEmail(email: String) {
        _state.update {
            it.copy(email = email, emailError = null)
        }
    }

    fun updatePassword(password: String) {
        _state.update {
            it.copy(password = password, passwordError = null)
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _state.update {
            it.copy(confirmPassword = confirmPassword, confirmPasswordError = null)
        }
    }

    fun toggleMode() {
        _state.update {
            it.copy(
                isLoginMode = !it.isLoginMode,
                emailError = null,
                passwordError = null,
                confirmPasswordError = null
            )
        }
    }

    fun authenticate() {
        val currentState = _state.value

        if (!validateInputs()) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val result = if (currentState.isLoginMode) {
                    repository.login(currentState.email, currentState.password)
                } else {
                    repository.register(currentState.email, currentState.password)
                }

                result.fold(
                    onSuccess = {
                        _state.update { it.copy(isLoading = false) }
                        appNavigation.navigateAndClearBackstack(Screen.Home.route)
                    },
                    onFailure = { error ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                hasShownError = false
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        hasShownError = false
                    )
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val currentState = _state.value
        var hasError = false

        // Email validation
        if (currentState.email.isBlank()) {
            _state.update { it.copy(emailError = "Email is required") }
            hasError = true
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(currentState.email).matches()) {
            _state.update { it.copy(emailError = "Please enter a valid email") }
            hasError = true
        }

        // Password validation
        if (currentState.password.isBlank()) {
            _state.update { it.copy(passwordError = "Password is required") }
            hasError = true
        } else if (currentState.password.length < 6) {
            _state.update { it.copy(passwordError = "Password must be at least 6 characters") }
            hasError = true
        }

        // Confirm password validation (only for register mode)
        if (!currentState.isLoginMode) {
            if (currentState.confirmPassword.isBlank()) {
                _state.update { it.copy(confirmPasswordError = "Please confirm your password") }
                hasError = true
            } else if (currentState.password != currentState.confirmPassword) {
                _state.update { it.copy(confirmPasswordError = "Passwords do not match") }
                hasError = true
            }
        }

        return !hasError
    }

    fun navigateBack() {
        appNavigation.navigateBack()
    }
}