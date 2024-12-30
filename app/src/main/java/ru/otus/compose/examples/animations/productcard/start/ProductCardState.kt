package ru.otus.compose.examples.animations.productcard.start

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ProductCardState(
    val title: String,
    val description: String,
    val images: ImagesState,
    val colors: ColorsState,
    val sizes: SizesState,
) {
    data class ImagesState(
        val imagesRes: ImmutableList<Int>,
        val currentImage: Int = 0,
    )

    data class ColorsState(
        val colors: ImmutableList<ColorState>,
        val currentColor: Int = 0,
    ) {
        data class ColorState(
            val colorName: String,
            val color: Color,
        )
    }

    data class SizesState(
        val sizes: ImmutableList<String>,
        val currentSize: Int = 0,
    )
}
