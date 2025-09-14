package com.codemockup.ecommercecommunity.features.screens.home.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.features.shared.components.cards.VerticalItem
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import com.codemockup.ecommercecommunity.features.theme.FontBlack

@Composable
fun RecommendedSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            "Recommended Combo",
            modifier = Modifier.padding(horizontal = 24.dp),
            style = MaterialTheme.typography.titleLarge.copy(color = FontBlack)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(4) {
                VerticalItem(
                    modifier = Modifier.fillParentMaxWidth(0.5F),
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true, device = PIXEL_4_XL)
@Composable
fun RecommendedSectionPreview() {
    EcommerceCommunityTheme {
        RecommendedSection()
    }
}