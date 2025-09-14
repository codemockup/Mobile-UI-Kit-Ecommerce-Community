package com.codemockup.ecommercecommunity.features.shared.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.common.enums.ButtonSize
import com.codemockup.ecommercecommunity.common.enums.ButtonType
import com.codemockup.ecommercecommunity.features.shared.components.buttons.MainIconButton
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import com.codemockup.ecommercecommunity.features.theme.FontBlack
import com.codemockup.ecommercecommunity.features.theme.Gray100
import com.codemockup.ecommercecommunity.features.theme.Primary400
import com.codemockup.ecommercecommunity.features.theme.White
import com.codemockup.ecommercecommunity.utils.extensions.shadowCustom

@Composable
fun VerticalItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .shadowCustom(
                color = Gray100,
                offsetX = 0.dp,
                offsetY = 0.dp,
                blurRadius = 8.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(White),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.dummy_item),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(0.8F)
            )
            Text(
                "Honey lime combo",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = FontBlack,
                    fontWeight = FontWeight.Medium
                )
            )
            /// price and add to cart button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "IDR 20000",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Primary400,
                    )
                )
                MainIconButton(
                    icon = R.drawable.ic_plus,
                    type = ButtonType.PRIMARY,
                    size = ButtonSize.SMALL,
                    onClick = {}
                )
            }
        }

        Box(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.TopEnd)) {
            MainIconButton(
                icon = R.drawable.ic_heart,
                type = ButtonType.PRIMARY,
                size = ButtonSize.MEDIUM,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, device = PIXEL_4_XL)
@Composable
fun VerticalItemPreview() {
    EcommerceCommunityTheme {
        VerticalItem(
            modifier = Modifier.fillMaxWidth(0.75F),
            onClick = {}
        )
    }
}