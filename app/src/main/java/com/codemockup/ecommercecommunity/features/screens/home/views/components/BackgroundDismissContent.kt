package com.codemockup.ecommercecommunity.features.screens.home.views.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.features.theme.Carmine400

@Composable
fun BackgroundDismissContent(
    dismissState: SwipeToDismissBoxState,
) {
    val color =
        animateColorAsState(
            when (dismissState.targetValue) {
                SwipeToDismissBoxValue.Settled -> Color.Transparent
                SwipeToDismissBoxValue.StartToEnd -> Carmine400
                SwipeToDismissBoxValue.EndToStart -> Carmine400
            }, label = ""
        )

    val textColor =
        animateColorAsState(
            when (dismissState.targetValue) {
                SwipeToDismissBoxValue.Settled -> Color.Transparent
                SwipeToDismissBoxValue.StartToEnd -> Color.White
                SwipeToDismissBoxValue.EndToStart -> Color.White
            }, label = ""
        )
    Box(
        Modifier
            .padding(bottom = 16.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .fillMaxSize()
            .background(color.value)
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "",
                tint = textColor.value,
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Delete", style = MaterialTheme.typography.titleMedium.copy(
                    color = textColor.value
                )
            )
        }
    }
}