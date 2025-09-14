package com.codemockup.ecommercecommunity.features.shared.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.common.enums.ButtonSize
import com.codemockup.ecommercecommunity.common.enums.ButtonType
import com.codemockup.ecommercecommunity.features.theme.Black
import com.codemockup.ecommercecommunity.features.theme.Primary100
import com.codemockup.ecommercecommunity.features.theme.White

@Composable
fun MainIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Int,
    type: ButtonType = ButtonType.PRIMARY,
    buttonContainer: Color? = null,
    buttonContentColor: Color? = null,
    borderColor: Color? = null,
    size: ButtonSize = ButtonSize.LARGE
) {
    val buttonColor = when (type) {
        ButtonType.PRIMARY -> Primary100
        ButtonType.SECONDARY -> White
    }
    val contentColor = when (type) {
        ButtonType.PRIMARY -> White
        ButtonType.SECONDARY -> Black
    }
    val iconSize = when (size) {
        ButtonSize.SMALL -> 24.dp
        ButtonSize.MEDIUM -> 32.dp
        ButtonSize.LARGE -> 56.dp
    }
    val iconContentPadding = when (size) {
        ButtonSize.SMALL -> 6.dp
        ButtonSize.MEDIUM -> 8.dp
        ButtonSize.LARGE -> 14.dp
    }
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(iconSize)
            .border(
                width = 1.dp,
                color = borderColor ?: Color.Transparent,
                shape = CircleShape
            ),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = buttonContainer ?: buttonColor,
            contentColor = buttonContentColor ?: contentColor
        ),
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(iconContentPadding),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainIconButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MainIconButton(
            onClick = {},
            icon = R.drawable.ic_plus,
            size = ButtonSize.SMALL,
            type = ButtonType.PRIMARY
        )
        MainIconButton(
            onClick = {},
            icon = R.drawable.ic_plus,
            size = ButtonSize.MEDIUM,
            type = ButtonType.PRIMARY
        )
        MainIconButton(
            onClick = {},
            icon = R.drawable.ic_plus,
            size = ButtonSize.LARGE,
            type = ButtonType.PRIMARY,
        )
    }
}