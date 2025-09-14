package com.codemockup.ecommercecommunity.features.screens.authentication.states

data class AuthenticationStates(
    val isLoading: Boolean = false,
    val hasShownError: Boolean = false,
    val name: String = ""
) {
    val isFormValid: Boolean = name.isNotBlank()
}