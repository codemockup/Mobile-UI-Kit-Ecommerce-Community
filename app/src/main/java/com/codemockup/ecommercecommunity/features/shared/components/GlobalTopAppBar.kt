package com.codemockup.ecommercecommunity.features.shared.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.codemockup.ecommercecommunity.features.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalTopAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    action: @Composable () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = White,
        ),
        navigationIcon = {
            navigationIcon()
        },
        title = {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
        },
        actions = {
            action()
        }
    )
}