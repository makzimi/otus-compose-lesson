package ru.otus.compose.examples.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Composable
fun Q1() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .padding(10.dp)
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.beans),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun PreviewQ1() {
    Surface { Q1() }
}

@Composable
fun Q2() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .requiredSize(20.dp)
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.beans),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun PreviewQ2() {
    Surface { Q2() }
}

@Composable
fun Q3() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .graphicsLayer {
                rotationZ = 45f
                shape = RoundedCornerShape(10)
                clip = true
            }
            .padding(10.dp)
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.beans),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun PreviewQ3() {
    Surface { Q3() }
}
