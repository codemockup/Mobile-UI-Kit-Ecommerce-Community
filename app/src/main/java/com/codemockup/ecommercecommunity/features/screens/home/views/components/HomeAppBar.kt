package com.codemockup.ecommercecommunity.features.screens.home.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.common.enums.ButtonSize
import com.codemockup.ecommercecommunity.common.enums.ButtonType
import com.codemockup.ecommercecommunity.features.shared.components.buttons.MainIconButton

@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {},
    onBasketClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainIconButton(
            icon = R.drawable.ic_menu,
            size = ButtonSize.LARGE,
            type = ButtonType.SECONDARY,
            onClick = onMenuClick
        )

        MainIconButton(
            icon = R.drawable.ic_basket,
            size = ButtonSize.LARGE,
            type = ButtonType.SECONDARY,
            onClick = onBasketClick
        )

    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun HomeAppBarPreview() {
    HomeAppBar()
}