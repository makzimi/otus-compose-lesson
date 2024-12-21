package ru.otus.compose.examples.modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
fun Chain(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = modifier
            .size(300.dp)
            .background(color = Color.Red)
            .padding(20.dp)
            .background(color = Color.Blue)
            .padding(20.dp)
            .background(color = Color.Green)
            .padding(20.dp)
    )
}

@Preview
@Composable
fun ChainPreview() {
    Surface {
        Chain(
            imageRes = R.drawable.catanddot,
            modifier = Modifier
        )
    }
}