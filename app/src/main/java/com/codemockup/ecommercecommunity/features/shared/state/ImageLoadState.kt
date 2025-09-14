package com.codemockup.ecommercecommunity.features.shared.state

// data load state
sealed class ImageLoadState {
    data object Loading : ImageLoadState()
    data object Error : ImageLoadState()
    data object Success : ImageLoadState()
    data object Empty : ImageLoadState()
}