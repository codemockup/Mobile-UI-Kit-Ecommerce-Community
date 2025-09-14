package com.codemockup.ecommercecommunity.features

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codemockup.ecommercecommunity.features.navigation.AppNavigationGraph

@Composable
fun EcommerceCommunity(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    Surface(
        modifier = modifier
    ) {
        AppNavigationGraph(navController = navController)
    }

}