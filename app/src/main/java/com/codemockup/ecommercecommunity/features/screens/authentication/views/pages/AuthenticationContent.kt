package com.codemockup.ecommercecommunity.features.screens.authentication.views.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.features.screens.authentication.states.AuthenticationStates
import com.codemockup.ecommercecommunity.features.shared.components.buttons.MainButton
import com.codemockup.ecommercecommunity.features.shared.components.inputs.PrimaryInput
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import com.codemockup.ecommercecommunity.features.theme.Primary400
import com.codemockup.ecommercecommunity.features.theme.White

@Composable
fun AuthenticationContent(
    state: AuthenticationStates = AuthenticationStates(),
    onNameChanged: (String) -> Unit = {},
    onStart: () -> Unit,
) {
    Scaffold(
        containerColor = Primary400
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            Box(
                modifier = Modifier
                    .weight(0.6F)
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.ill_fruit_basket_2),
                    contentDescription = stringResource(R.string.fruit_basket_desc),
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = Modifier
                    .background(White)
                    .padding(horizontal = 24.dp)
                    .weight(0.4F),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    PrimaryInput(
                        value = state.name,
                        onValueChange = onNameChanged,
                        label = stringResource(R.string.what_is_your_name),
                        hint = stringResource(R.string.fortune_cookie)
                    )
                    MainButton(
                        title = stringResource(R.string.start_ordering),
                        enabled = state.isFormValid,
                        onClick = onStart
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationContentPreview() {
    EcommerceCommunityTheme {
        AuthenticationContent(
            onNameChanged = {},
            onStart = {}
        )
    }
}