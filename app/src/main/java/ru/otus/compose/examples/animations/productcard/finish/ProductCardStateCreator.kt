package ru.otus.compose.examples.animations.productcard.finish

import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.persistentListOf
import ru.otus.compose.R
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ColorsState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ColorsState.ColorState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ImagesState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ImagesState.ImageState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.SizesState

object ProductCardStateCreator {
    fun create(
        selectedImage: Int,
        selectedColor: Int,
        selectedSize: Int,
        isLiked: Boolean,
    ): ProductCardState {
        return ProductCardState(
            title = "Cozy Cat Sweater",
            description = "Keep your feline friend warm and stylish with this adorable cozy sweater. Perfect for chilly days, it ensures comfort and a snug fit for cats of all sizes. Designed with love and attention to detail, your cat will purr with delight!",
            images = ImagesState(
                imagesRes = persistentListOf(
                    ImageState(R.drawable.sweater_blue, false),
                    ImageState(R.drawable.sweater_red, false),
                    ImageState(R.drawable.sweater_yellow, false),
                    ImageState(R.drawable.sweater_green, false),
                    ImageState(R.drawable.sweater_pink, true),
                ),
                currentImage = selectedImage,

            ),
            colors = ColorsState(
                colors = persistentListOf(
                    ColorState("Midnight Blue", Color(0xFF001F54), false),
                    ColorState("Crimson Red", Color(0xFFDC143C), false),
                    ColorState("Sunny Yellow", Color(0xFFFFD700), false),
                    ColorState("Forest Green", Color(0xFF228B22), false),
                    ColorState("Blush Pink", Color(0xFFFFC0CB), true),
                ),
                currentColor = selectedColor,
            ),
            sizes = SizesState(
                sizes = persistentListOf("XS", "S", "M", "L", "XL"),
                currentSize = selectedSize,
            ),
            isLiked = isLiked,
        )
    }
}