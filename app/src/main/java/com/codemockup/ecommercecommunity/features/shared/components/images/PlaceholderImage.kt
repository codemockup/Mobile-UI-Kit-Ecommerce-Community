package com.codemockup.ecommercecommunity.features.shared.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codemockup.ecommercecommunity.R
import com.codemockup.ecommercecommunity.features.theme.Gray300
import com.codemockup.ecommercecommunity.features.theme.Surface0


/**
 * A composable that shows a placeholder image when the network image is not available.
 * The placeholder can be customized for avatars or other image types.
 *
 * @param modifier Modifier to customize the placeholder view.
 * @param isAvatar A flag to indicate if the placeholder is for an avatar image (used to determine the default placeholder).
 * @param placeholderResourceId Optional resource ID for a custom placeholder image.
 * @param shape The shape of the placeholder (defaults to rounded corners).
 */
@Composable
fun PlaceholderImage(
    modifier: Modifier,
    isAvatar: Boolean = false,
    placeholderResourceId: Int? = null,
    shape: Shape = RoundedCornerShape(10.dp),
) {
    val imageResource = placeholderResourceId
        ?: if (isAvatar) R.drawable.ic_person_solid else R.drawable.ic_image_solid

    Box(
        modifier = modifier
            .clip(shape)
            .then(
                if (placeholderResourceId == null) {
                    Modifier.Companion.background(Gray300)
                } else Modifier
            )
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = if (placeholderResourceId == null) {
                ColorFilter.tint(Surface0, BlendMode.SrcIn)
            } else null
        )
    }
}