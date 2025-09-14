package com.codemockup.ecommercecommunity.features.screens.welcome.views.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.navigation.Screen
import com.codemockup.ecommercecommunity.features.screens.welcome.viewmodel.WelcomeViewModel
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun WelcomePage(
    viewModel: WelcomeViewModel = koinViewModel(),
    appNavigation: AppNavigation = koinInject(),
) {
    val context = LocalContext.current

    /// viewmodel state
    val state by viewModel.state.collectAsState()

    WelcomeContent(
        state = state,
        onContinue = { appNavigation.navigateTo(Screen.Authentication.route) }
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun WelcomePagePreview() {
    EcommerceCommunityTheme {
        WelcomePage()
    }
}