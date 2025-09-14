package com.codemockup.ecommercecommunity.features.shared.state

sealed class UiState<out T : Any?> {

    data object Idle : UiState<Nothing>()

    data object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()

    data object Empty : UiState<Nothing>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}