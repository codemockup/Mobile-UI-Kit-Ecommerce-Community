package com.codemockup.ecommercecommunity.features.screens.home.views.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codemockup.ecommercecommunity.features.navigation.AppNavigation
import com.codemockup.ecommercecommunity.features.navigation.Screen
import com.codemockup.ecommercecommunity.features.shared.components.GlobalTopAppBar
import com.codemockup.ecommercecommunity.features.theme.White
import org.koin.compose.koinInject

@Composable
fun HomeContent(
    appNavigation: AppNavigation = koinInject(),
) {
    Scaffold(
        containerColor = White,
        topBar = {
            GlobalTopAppBar(
                title = "Fruit List",
                action = {
                    IconButton(
                        onClick = {
                            appNavigation.navigateTo(Screen.BeanAdd.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "add"
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) { }
    }
}
