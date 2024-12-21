package ru.otus.compose.examples.modifier.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.otus.compose.R

// padding
// background
// size
// fillMaxSize
// align
// wrapContentSize
// requiredSize
// clickable
// offset
// alpha
// border
// clip
// graphicsLayer
@Composable
fun TestCommon(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun TestCommonPreview() {
    Surface(modifier = Modifier) {
        TestCommon(
            imageRes = R.drawable.catanddot,
            modifier = Modifier
        )
    }
}
