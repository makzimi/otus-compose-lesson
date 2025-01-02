package ru.otus.compose.examples.layout.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    Box(modifier) {
        background()
        foreground(IntSize(300, 300))
    }
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
