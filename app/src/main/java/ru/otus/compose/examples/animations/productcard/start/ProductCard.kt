package ru.otus.compose.examples.animations.productcard.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.ImagesState.ImageState
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.ColorsState
import ru.otus.compose.examples.animations.productcard.start.ProductCardState.SizesState

/**
 * Task:
 * - Анимируем сообщение Out of Stock
 * - Анимируем значок “!”
 * - Аниминуем появиление Size Controls (пусть они выходят с разных сторон)
 * - Анимируем смену картинок (котиков)
 * - Анимируем имя цвета
 * - Анимируем кружочек у выбора цвета
 * - Анимируем size
 * - Сделать прыгающий бейджик Discount
 * - AnimatedImageVector. Сделать анимационную иконку лайка
 */

@Composable
fun ProductCard(
    state: ProductCardState,
    modifier: Modifier = Modifier,
    onColorClicked: (Int) -> Unit = { },
    onSizeClicked: (Int) -> Unit = { },
    onLiked: (Boolean) -> Unit = { },
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(state.title) },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(
                    top = innerPadding.calculateTopPadding() + 12.dp,
                    bottom = innerPadding.calculateBottomPadding() + 12.dp,
                ),
            ) {
                Images(
                    image = state.images.imagesRes[state.images.currentImage],
                    modifier = Modifier,
                )
                ColorControls(
                    state = state.colors,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 20.dp),
                    onColorClicked = onColorClicked,
                )
                if (!state.colors.colors[state.colors.currentColor].outOfStock) {
                    SizesControls(
                        state = state.sizes,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 20.dp),
                        onSizeClicked = onSizeClicked,
                    )
                }
                AboutProduct(
                    description = state.description,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 20.dp)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors = topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        title = {
            Text(
                text = title,
                modifier = modifier,
                style = MaterialTheme.typography.titleLarge
            )
        },
    )
}

@Composable
fun AboutProduct(
    description: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
fun Images(
    image: ImageState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image.imageRes),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .aspectRatio(1f)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .then(
                    if (image.outOfStock) {
                        Modifier.alpha(0.5f)
                    } else {
                        Modifier
                    }
                )
        )

        if (image.outOfStock) {
            Text(
                text = "Out of stock 😢",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(20.dp)
                    .graphicsLayer {
                        shadowElevation = 12f
                        shape = RoundedCornerShape(20.dp)
                        clip = true
                    }
                    .background(color = MaterialTheme.colorScheme.errorContainer)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ColorControls(
    state: ColorsState,
    modifier: Modifier = Modifier,
    onColorClicked: (Int) -> Unit = { },
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            Text(
                text = "Color: ",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = state.colors[state.currentColor].colorName,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.colors.forEachIndexed { index, colorState ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .then(
                            if (index == state.currentColor) {
                                Modifier.border(
                                    width = 2.dp,
                                    color = if (colorState.outOfStock) {
                                        MaterialTheme.colorScheme.error
                                    } else {
                                        MaterialTheme.colorScheme.primary
                                    },
                                    shape = CircleShape,
                                )
                            } else {
                                Modifier
                            }
                        )
                        .size(30.dp)
                        .clickable { onColorClicked(index) }
                ) {
                    Spacer(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(color = colorState.color)
                            .size(20.dp)
                    )
                }
            }

            if (state.colors[state.currentColor].outOfStock) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(CenterVertically)
                )
            }
        }
    }
}

@Composable
fun SizesControls(
    state: SizesState,
    modifier: Modifier = Modifier,
    onSizeClicked: (Int) -> Unit = { },
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Size: ",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .height(64.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = CenterVertically,
        ) {
            state.sizes.forEachIndexed { index, size ->
                Text(
                    text = size,
                    modifier = Modifier
                        .size(48.dp)
                        .then(
                            if (index == state.currentSize) {
                                Modifier.border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                            } else {
                                Modifier
                                    .alpha(0.3f)
                                    .border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clip(RoundedCornerShape(8.dp))
                            }
                        )
                        .wrapContentHeight()
                        .clickable { onSizeClicked(index) },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    Surface {
        var selectedImage by remember { mutableIntStateOf(0) }
        var selectedColor by remember { mutableIntStateOf(0) }
        var selectedSize by remember { mutableIntStateOf(2) }
        var isLiked by remember { mutableStateOf(false) }

        ProductCard(
            modifier = Modifier.fillMaxSize(),
            state = ProductCardStateCreator.create(
                selectedImage = selectedImage,
                selectedColor = selectedColor,
                selectedSize = selectedSize,
                isLiked = isLiked
            ),
            onColorClicked = { newColor ->
                selectedColor = newColor
                selectedImage = newColor
            },
            onSizeClicked = { newSize ->
                selectedSize = newSize
            },
            onLiked = { newIsLiked ->
                isLiked = !newIsLiked
            }
        )
    }
}
