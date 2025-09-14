package com.codemockup.ecommercecommunity.features.screens.authentication.views.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.screens.authentication.viewmodel.AuthenticationViewModel
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthenticationPage(
    viewModel: AuthenticationViewModel = koinViewModel(),
) {
    val context = LocalContext.current

    /// viewmodel state
    val state by viewModel.state.collectAsState()

    AuthenticationContent(
        state = state
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun AuthenticationPagePreview() {
    EcommerceCommunityTheme {
        AuthenticationPage()
    }
}