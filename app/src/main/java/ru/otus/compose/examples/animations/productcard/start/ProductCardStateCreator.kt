package ru.otus.compose.examples.animations.productcard.start

import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.persistentListOf
import ru.otus.compose.R
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.ColorsState
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.ColorsState.ColorState
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.ImagesState
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.SizesState

object ProductCardStateCreator {
    fun create(
        selectedImage: Int,
        selectedColor: Int,
        selectedSize: Int,
    ): ProductCardState {
        return ProductCardState(
            title = "Cozy Cat Sweater",
            description = "Keep your feline friend warm and stylish with this adorable cozy sweater. Perfect for chilly days, it ensures comfort and a snug fit for cats of all sizes. Designed with love and attention to detail, your cat will purr with delight!",
            images = ImagesState(
                imagesRes = persistentListOf(
                    R.drawable.sweater_blue,
                    R.drawable.sweater_red,
                    R.drawable.sweater_yellow,
                    R.drawable.sweater_green,
                    R.drawable.sweater_pink,
                ),
                currentImage = selectedImage
            ),
            colors = ColorsState(
                colors = persistentListOf(
                    ColorState("Midnight Blue", Color(0xFF001F54)),
                    ColorState("Crimson Red", Color(0xFFDC143C)),
                    ColorState("Sunny Yellow", Color(0xFFFFD700)),
                    ColorState("Forest Green", Color(0xFF228B22)),
                    ColorState("Blush Pink", Color(0xFFFFC0CB)),
                ),
                currentColor = selectedColor,
            ),
            sizes = SizesState(
                sizes = persistentListOf("XS", "S", "M", "L", "XL"),
                currentSize = selectedSize,
            )
        )
    }
}