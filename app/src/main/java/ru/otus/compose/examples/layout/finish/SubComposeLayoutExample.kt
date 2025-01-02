package ru.otus.compose.examples.layout.finish

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

/**
 * Task:
 *
 * Make Composable that draw foreground depending on background size
 */
@Composable
fun BackgroundForegroundLayout(
    background: @Composable () -> Unit,
    foreground: @Composable (IntSize) -> Unit,
    modifier: Modifier = Modifier,
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val bgPlaceables = subcompose(SlotsEnum.Background, background).map { measurable ->
            measurable.measure(constraints)
        }
        val maxSize =
            bgPlaceables.fold(IntSize.Zero) { currentMax, placeable ->
                IntSize(
                    width = maxOf(currentMax.width, placeable.width),
                    height = maxOf(currentMax.height, placeable.height)
                )
            }

        layout(maxSize.width, maxSize.height) {
            bgPlaceables.forEach { placeable -> placeable.placeRelative(0, 0) }
            subcompose(SlotsEnum.Foreground) { foreground(maxSize) }
                .forEach { measurable ->
                    measurable.measure(constraints).placeRelative(0, 0)
                }
        }
    }
}

enum class SlotsEnum {
    Background,
    Foreground;
}

@Preview
@Composable
fun BackgroundForegroundLayoutPreview() {
    Surface {
        BackgroundForegroundLayout(
            background = {
                Image(
                    painter = painterResource(id = R.drawable.catanddot),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
            },
            foreground = { maxSize ->
                if (maxSize.width < 300) {
                    Text(
                        text = "K!",
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp)
                    )
                } else {
                    Text(
                        text = "Kitty!",
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp)
                    )
                }

            }
        )
    }
}
