package com.codemockup.ecommercecommunity.features.screens.authentication.views.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.screens.authentication.states.AuthenticationStates
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme

@Composable
fun AuthenticationContent(
    state: AuthenticationStates = AuthenticationStates(),
) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationContentLoginPreview() {
    EcommerceCommunityTheme {
        AuthenticationContent()
    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationContentRegisterPreview() {
    EcommerceCommunityTheme {
        AuthenticationContent()
    }
}