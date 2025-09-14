package com.codemockup.ecommercecommunity.features.shared.components.inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.features.theme.FontBlack
import com.codemockup.ecommercecommunity.features.theme.Gray100
import com.codemockup.ecommercecommunity.features.theme.Gray400
import com.codemockup.ecommercecommunity.features.theme.Primary400

@Composable
fun PrimaryInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    label: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        label?.let {
            Text(
                it,
                style = MaterialTheme.typography.titleMedium.copy(color = FontBlack)
            )
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            placeholder = {
                if (hint.isNotBlank()) Text(
                    hint,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Gray400,
                        fontWeight = FontWeight.Normal,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                )
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = FontBlack,
                fontWeight = FontWeight.Normal
            ),
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Gray100,
                unfocusedContainerColor = Gray100,
                disabledContainerColor = Gray100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Primary400,
                focusedTextColor = FontBlack,
                unfocusedTextColor = FontBlack
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PrimaryInputPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PrimaryInput(
            value = "",
            onValueChange = {},
            hint = "Hint"
        )

        PrimaryInput(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = Gray400
                )
            },
            hint = "Hint"
        )
    }
}