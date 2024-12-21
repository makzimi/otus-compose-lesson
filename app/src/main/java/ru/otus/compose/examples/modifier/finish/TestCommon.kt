package ru.otus.compose.examples.modifier.finish

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Composable
fun TestCommon(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        //.size(300.dp)
        .wrapContentSize()
        .background(color = MaterialTheme.colorScheme.primaryContainer)
        .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .clickable { println("Clicked!") }
                .align(Alignment.Center)
                //.fillMaxSize()
                //.requiredSize(800.dp)
                .size(200.dp)
                .offset(x = 20.dp, y = 20.dp)
                //.alpha(0.5f)
                //.border(2.dp, Color.Black, CircleShape)
                .graphicsLayer {
                    shape = RoundedCornerShape(10.dp)
                    rotationZ = 15f
                    shadowElevation = 3f
                    clip = true
                }
                //.clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun TestCommonPreview() {
    Surface(modifier = Modifier.size(300.dp)) {
        TestCommon(
            imageRes = R.drawable.catanddot,
            modifier = Modifier
        )
    }
}
