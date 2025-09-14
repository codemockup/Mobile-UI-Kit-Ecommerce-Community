package com.codemockup.ecommercecommunity.features.shared.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.common.enums.ButtonType
import com.codemockup.ecommercecommunity.features.theme.Primary400
import com.codemockup.ecommercecommunity.features.theme.White

@Composable
fun MainButton(
    title: String = stringResource(R.string.tap_me),
    type: ButtonType = ButtonType.PRIMARY,
    wrap: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onClick: () -> Unit = {}
) {
    val buttonColor = when (type) {
        ButtonType.PRIMARY -> Primary400
        ButtonType.SECONDARY -> White
    }
    val buttonContentColor = when (type) {
        ButtonType.PRIMARY -> White
        ButtonType.SECONDARY -> Primary400
    }

    Button(
        modifier = Modifier.then(
            if (!wrap) {
                Modifier.fillMaxWidth()
            } else {
                Modifier
            }
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = buttonContentColor
        ),
        border = BorderStroke(2.dp, buttonContentColor),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(contentPadding),
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MainButton()
        MainButton(
            type = ButtonType.SECONDARY
        )
    }
}