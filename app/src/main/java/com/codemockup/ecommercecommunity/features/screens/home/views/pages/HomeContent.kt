package com.codemockup.ecommercecommunity.features.screens.home.views.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.features.screens.home.views.components.HomeAppBar
import com.codemockup.ecommercecommunity.features.screens.home.views.components.HomeHeader
import com.codemockup.ecommercecommunity.features.screens.home.views.components.RecommendedSection
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import com.codemockup.ecommercecommunity.features.theme.White

@Composable
fun HomeContent(
    onMenuClick: () -> Unit = {},
    onBasketClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = White,
        topBar = {
            HomeAppBar(
                onMenuClick = onMenuClick,
                onBasketClick = onBasketClick
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
            ),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            HomeHeader(
                modifier = Modifier.padding(horizontal = 24.dp),
                search = "",
                onValueChange = {},
                onFilterClick = {}
            )
            RecommendedSection()
        }
    }
}

@Preview(showBackground = true, device = PIXEL_4_XL)
@Composable
fun HomeContentPreview() {
    EcommerceCommunityTheme {
        HomeContent(
            onMenuClick = {},
            onBasketClick = {}
        )
    }
}

