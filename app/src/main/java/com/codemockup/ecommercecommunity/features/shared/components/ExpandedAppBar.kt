package com.codemockup.ecommercecommunity.features.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.features.shared.components.buttons.PlainButton
import com.codemockup.ecommercecommunity.features.theme.Primary400
import com.codemockup.ecommercecommunity.features.theme.White

@Composable
fun ExpandedAppBar(
    modifier: Modifier = Modifier,
    title: String = "Welcome",
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(Primary400)
            .padding(
                horizontal = 24.dp,
                vertical = 32.dp
            )
            .fillMaxWidth()
    ) {
        PlainButton(
            modifier = Modifier.align(Alignment.CenterStart),
            title = stringResource(R.string.go_back),
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = null
                )
            },
            onClick = onBackClick
        )

        Text(
            title,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge.copy(
                color = White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandedAppBarPreview() {
    ExpandedAppBar()
}