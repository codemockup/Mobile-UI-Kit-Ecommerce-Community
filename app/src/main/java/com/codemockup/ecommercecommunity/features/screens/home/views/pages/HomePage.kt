package com.codemockup.ecommercecommunity.features.screens.home.views.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.screens.home.viewmodel.HomeViewModel
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val context = LocalContext.current

    /// viewmodel state
    val state by viewModel.state.collectAsState()

    HomeContent()
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview(
) {
    EcommerceCommunityTheme {
        HomePage()
    }
}
