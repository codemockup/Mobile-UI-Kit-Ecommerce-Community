package com.codemockup.ecommercecommunity.features.screens.authentication.states

data class AuthenticationStates(
    val isLoading: Boolean = false,
    val hasShownError: Boolean = false,
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoginMode: Boolean = true,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
)