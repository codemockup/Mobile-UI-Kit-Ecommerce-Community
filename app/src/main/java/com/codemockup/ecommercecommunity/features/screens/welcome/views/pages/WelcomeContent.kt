package com.codemockup.ecommercecommunity.features.screens.welcome.views.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.screens.welcome.states.WelcomeStates
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme

@Composable
fun WelcomeContent(
    state: WelcomeStates = WelcomeStates(),
) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeContentPreview() {
    EcommerceCommunityTheme {
        WelcomeContent()
    }
}