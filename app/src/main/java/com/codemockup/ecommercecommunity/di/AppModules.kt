package com.codemockup.ecommercecommunity.di

import com.codemockup.ecommercecommunity.data.providers.StringResourceProvider
import com.codemockup.ecommercecommunity.data.providers.StringResourceProviderImpl
import com.codemockup.ecommercecommunity.data.remote.retrofit.ApiConfig
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.screens.authentication.repositories.AuthenticationRepository
import com.codemockup.ecommercecommunity.features.screens.authentication.viewmodel.AuthenticationViewModel
import com.codemockup.ecommercecommunity.features.screens.home.repositories.HomeRepository
import com.codemockup.ecommercecommunity.features.screens.home.viewmodel.HomeViewModel
import com.codemockup.ecommercecommunity.features.screens.welcome.repositories.WelcomeRepository
import com.codemockup.ecommercecommunity.features.screens.welcome.viewmodel.WelcomeViewModel
import com.codemockup.ecommercecommunity.features.shared.viewmodel.GlobalViewModel
import com.codemockup.ecommercecommunity.utils.services.LocationServices
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    /* Services */
    single { LocationServices(get()) }

    /* Providers */
    single<StringResourceProvider> { StringResourceProviderImpl(get()) }

    /* Network */
    single<ApiConfig> { ApiConfig() }
    factory { ApiConfig.getApiService() }

    /* Repositories */
    single { HomeRepository(get(), get()) }
    single { WelcomeRepository(get(), get()) }
    single { AuthenticationRepository(get(), get()) }

    /* Navigation singleton */
    single { AppNavigation() }

    /* ViewModels  */
    viewModel { GlobalViewModel() }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { WelcomeViewModel(get(), get()) }
    viewModel { AuthenticationViewModel(get(), get()) }
}