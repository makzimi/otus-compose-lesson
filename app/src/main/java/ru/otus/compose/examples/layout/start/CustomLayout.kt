package ru.otus.compose.examples.layout.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {

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