package ru.otus.compose.examples.layout.finish

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

/**
 * Task:
 *
 * Make Layout that put items by diagonal
 */
@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(
                Constraints(
                    maxWidth = constraints.maxWidth / measurables.size,
                    maxHeight = constraints.maxHeight / measurables.size,
                )
            )
        }

        layout(
            // 1
            placeables.fold(0) { acc, n -> acc + n.width },
            placeables.fold(0) { acc, n -> acc + n.height },
            // 2
//            constraints.maxWidth,
//            constraints.maxHeight,
        ) {
            var x = 0
            var y = 0
            placeables.forEach { placable ->
                placable.place(x, y)
                x += placable.width
                y += placable.height
            }
        }
    }
}

@Preview
@Composable
fun CustomLayoutPreview() {
    Surface {
        CustomLayout(
            modifier = Modifier
                .size(300.dp)
                .background(color = Color.Green)
        ) {
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp) // w: 0-80; h: 0-80
                    .size(60.dp) // w: 60-60; h: 60-60
                    .clip(CircleShape)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
            )
        }
    }
}