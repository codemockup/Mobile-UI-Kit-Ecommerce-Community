package com.codemockup.ecommercecommunity.features.screens.authentication.views.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.navigation.Screen
import com.codemockup.ecommercecommunity.features.screens.authentication.viewmodel.AuthenticationViewModel
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AuthenticationPage(
    viewModel: AuthenticationViewModel = koinViewModel(),
    appNavigation: AppNavigation = koinInject()
) {
    /// viewmodel state
    val state by viewModel.state.collectAsState()

    AuthenticationContent(
        state = state,
        onNameChanged = viewModel::updateName,
        onStart = { appNavigation.navigateAndClearBackstack(Screen.Home.route) }
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun AuthenticationPagePreview() {
    EcommerceCommunityTheme {
        AuthenticationPage()
    }
}