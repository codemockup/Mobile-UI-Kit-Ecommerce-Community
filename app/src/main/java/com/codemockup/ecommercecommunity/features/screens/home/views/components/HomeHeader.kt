package com.codemockup.ecommercecommunity.features.screens.home.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.common.enums.ButtonSize
import com.codemockup.ecommercecommunity.common.enums.ButtonType
import com.codemockup.ecommercecommunity.features.shared.components.buttons.MainIconButton
import com.codemockup.ecommercecommunity.features.shared.components.inputs.PrimaryInput
import com.codemockup.ecommercecommunity.features.shared.components.text.AnnotatedText
import com.codemockup.ecommercecommunity.features.theme.FontBlack
import com.codemockup.ecommercecommunity.features.theme.Gray400

@Preview(showBackground = true, device = PIXEL_4_XL)
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    search: String = "",
    onValueChange: (String) -> Unit = {},
    onFilterClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AnnotatedText(
            modifier = Modifier.fillMaxWidth(0.8F),
            text = stringResource(R.string.home_greeting),
            highlightedText = stringResource(R.string.home_greeting_highlighted),
            textAlign = TextAlign.Start,
            normalColor = FontBlack,
            highlightColor = FontBlack,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PrimaryInput(
                modifier = Modifier.weight(1F),
                value = search,
                hint = stringResource(R.string.search_for_fruit_salad_combos),
                onValueChange = onValueChange,
                singleLine = true,
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        colorFilter = ColorFilter.tint(color = Gray400)
                    )
                }
            )
            MainIconButton(
                icon = R.drawable.ic_filter,
                size = ButtonSize.LARGE,
                type = ButtonType.SECONDARY,
                onClick = onFilterClick,
            )
        }

    }
}