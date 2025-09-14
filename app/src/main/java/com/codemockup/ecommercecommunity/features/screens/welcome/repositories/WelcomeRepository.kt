package com.codemockup.ecommercecommunity.features.screens.welcome.repositories

import com.codemockup.ecommercecommunity.data.providers.StringResourceProvider
import com.codemockup.ecommercecommunity.data.remote.ApiService
import com.codemockup.ecommercecommunity.data.repositories.BaseRepository

class WelcomeRepository(
    private val apiService: ApiService,
    stringProvider: StringResourceProvider,
) : BaseRepository(stringProvider = stringProvider) {
}