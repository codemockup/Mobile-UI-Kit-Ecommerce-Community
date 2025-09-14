package com.codemockup.ecommercecommunity.features.shared.components.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme
import com.codemockup.ecommercecommunity.features.theme.FontBlack
import com.codemockup.ecommercecommunity.features.theme.Primary400

@Composable
fun AnnotatedText(
    modifier: Modifier = Modifier,
    text: String,
    highlightedText: String = "",
    textAlign: TextAlign = TextAlign.Center,
    normalColor: Color = FontBlack,
    highlightColor: Color = Primary400,
    normalFontWeight: FontWeight = FontWeight.Normal,
    highlightFontWeight: FontWeight = FontWeight.Medium,
    onClick: ((String) -> Unit)? = null
) {
    val annotatedString = buildAnnotatedString {
        val startIndex = text.indexOf(highlightedText)

        if (startIndex != -1 && highlightedText.isNotEmpty()) {
            // Add text before highlighted part
            if (startIndex > 0) {
                withStyle(
                    style = SpanStyle(
                        color = normalColor,
                        fontWeight = normalFontWeight
                    )
                ) {
                    append(text.substring(0, startIndex))
                }
            }

            // Add highlighted text
            withStyle(
                style = SpanStyle(
                    color = highlightColor,
                    fontWeight = highlightFontWeight
                )
            ) {
                if (onClick != null) {
                    pushStringAnnotation(
                        tag = "CLICKABLE",
                        annotation = highlightedText
                    )
                }
                append(highlightedText)
                if (onClick != null) {
                    pop()
                }
            }

            // Add text after highlighted part
            val endIndex = startIndex + highlightedText.length
            if (endIndex < text.length) {
                withStyle(
                    style = SpanStyle(
                        color = normalColor,
                        fontWeight = normalFontWeight
                    )
                ) {
                    append(text.substring(endIndex))
                }
            }
        } else {
            // No highlighted text, show normal text
            withStyle(
                style = SpanStyle(
                    color = normalColor,
                    fontWeight = normalFontWeight
                )
            ) {
                append(text)
            }
        }
    }

    if (onClick != null) {
        ClickableText(
            text = annotatedString,
            modifier = modifier,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = textAlign),
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "CLICKABLE",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { annotation ->
                    onClick(annotation.item)
                }
            }
        )
    } else {
        androidx.compose.material3.Text(
            text = annotatedString,
            modifier = modifier,
            style = MaterialTheme.typography.titleLarge,
            textAlign = textAlign
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnnotatedTextPreview() {
    EcommerceCommunityTheme {
        AnnotatedText(
            text = "Hello there, good morning!",
            highlightedText = "good morning",
            onClick = { clickedText ->
                // Handle click
            }
        )
    }
}