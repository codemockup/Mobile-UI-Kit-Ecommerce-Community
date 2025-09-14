package com.codemockup.ecommercecommunity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.codemockup.ecommercecommunity.features.EcommerceCommunity
import com.codemockup.ecommercecommunity.features.theme.AppScrim
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import com.codemockup.ecommercecommunity.utils.extensions.toInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(AppScrim.toInt()))
        setContent {
            EcommerceCommunityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EcommerceCommunity()
                }
            }
        }
    }
}