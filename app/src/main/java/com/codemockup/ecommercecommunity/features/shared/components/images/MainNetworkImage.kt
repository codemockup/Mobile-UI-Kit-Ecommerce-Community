package com.codemockup.ecommercecommunity.features.shared.components.images

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.codemockup.ecommercecommunity.features.shared.state.ImageLoadState
import com.codemockup.ecommercecommunity.features.theme.EcommerceCommunityTheme

/**
 * A composable that loads and displays an image from a network URL.
 * It handles different loading states and shows a placeholder or the actual image
 * depending on the load status.
 *
 * @param modifier Modifier to customize the image view.
 * @param imageUrl The URL of the image to load.
 * @param contentScale Defines how the image should be scaled within the bounds of its container.
 * @param contentDescription The content description for the image (for accessibility).
 * @param colorFilter Optional color filter to apply to the image.
 * @param isAvatar A flag to indicate if the image is an avatar (used to determine the default placeholder).
 * @param shape The shape of the image's border (defaults to rounded corners).
 * @param placeholderResourceId Optional resource ID for a custom placeholder image.
 */
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = "",
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    backgroundColor: Color = Color.Transparent,
    isAvatar: Boolean = false,
    shape: Shape = RoundedCornerShape(10.dp),
    placeholderResourceId: Int? = null,
) {
    var imageState by remember {
        mutableStateOf<ImageLoadState>(ImageLoadState.Loading)
    }

    when (imageState) {
        /// error on empty show placeholder
        ImageLoadState.Empty, ImageLoadState.Error -> PlaceholderImage(
            modifier = modifier,
            isAvatar = isAvatar,
            shape = shape,
            placeholderResourceId = placeholderResourceId
        )

        else ->
            AsyncImage(
                modifier = modifier
                    .clip(shape = shape)
                    .background(backgroundColor),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true).build(),
                contentScale = contentScale,
                colorFilter = colorFilter,
                contentDescription = contentDescription ?: imageUrl,
                onState = { state ->
                    imageState = when (state) {
                        is AsyncImagePainter.State.Loading -> ImageLoadState.Loading
                        is AsyncImagePainter.State.Error -> ImageLoadState.Error
                        is AsyncImagePainter.State.Success -> ImageLoadState.Success
                        is AsyncImagePainter.State.Empty -> ImageLoadState.Empty
                    }
                },
            )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun MainNetworkImagePreview() {
    EcommerceCommunityTheme {
        NetworkImage()
    }
}

