package ru.otus.compose.examples.animations.productcard.finish

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ColorsState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.SizesState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ImagesState.ImageState
import ru.otus.compose.examples.animations.productcard.finish.ProductCardState.ImagesState

@Composable
fun ProductCard(
    state: ProductCardState,
    modifier: Modifier = Modifier,
    onColorClicked: (Int) -> Unit = { },
    onSizeClicked: (Int) -> Unit = { },
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
                    images = state.images,
                    modifier = Modifier,
                )
                ColorControls(
                    state = state.colors,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 20.dp),
                    onColorClicked = onColorClicked,
                )
                AnimatedVisibility(
                    visible = !state.colors.colors[state.colors.currentColor].outOfStock,
                    enter = fadeIn(tween(300, 300)) + expandVertically(tween(300, 0)),
                    exit = fadeOut(tween(300, 0)) + shrinkVertically(tween(300, 300)),
                ) {
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
    images: ImagesState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AnimatedContent(
            targetState = images.currentImage,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { height -> height } + fadeIn() togetherWith
                            slideOutHorizontally { height -> -height } + fadeOut()
                } else {
                    slideInHorizontally { height -> -height } + fadeIn() togetherWith
                            slideOutHorizontally { height -> height } + fadeOut()
                }
            }
        ) { targetState ->
            Image(
                painter = painterResource(images.imagesRes[targetState].imageRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .then(
                        if (images.imagesRes[targetState].outOfStock) {
                            Modifier.alpha(0.5f)
                        } else {
                            Modifier
                        }
                    )
            )
        }

        AnimatedVisibility(
            visible = images.imagesRes[images.currentImage].outOfStock,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
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
            AnimatedContent(
                targetState = state.colors[state.currentColor].colorName,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }
            ) { targetState ->
                Text(
                    text = targetState,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
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

            AnimatedVisibility(
                visible = state.colors[state.currentColor].outOfStock,
                enter = slideInHorizontally { 40 } + fadeIn(),
                exit = slideOutHorizontally { 40 } + fadeOut(),
                modifier = Modifier
                    .align(CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun AnimatedVisibilityScope.SizesControls(
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
            modifier = Modifier
                .padding(top = 8.dp)
                .animateEnterExit(
                    enter = slideInHorizontally(tween(300, 0)) { -40 },
                    exit = slideOutHorizontally(tween(300, 0)) { -40 },
                )
        )
        Row(
            modifier = Modifier
                .animateEnterExit(
                    enter = slideInHorizontally(tween(300, 300)) { 40 },
                    exit = slideOutHorizontally(tween(300, 300)) { 40 },
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.sizes.forEachIndexed { index, size ->
                Text(
                    text = size,
                    modifier = Modifier
                        .width(48.dp)
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
                        .padding(8.dp)
                        .clickable { onSizeClicked(index) },
                    textAlign = TextAlign.Center
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

        ProductCard(
            modifier = Modifier.fillMaxSize(),
            state = ProductCardStateCreator.create(selectedImage, selectedColor, selectedSize),
            onColorClicked = { newColor ->
                selectedColor = newColor
                selectedImage = newColor
            },
            onSizeClicked = { newSize ->
                selectedSize = newSize
            }
        )
    }
}
