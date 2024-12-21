package ru.otus.compose.examples.modifier.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TestAspectRatio(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Green)
            .aspectRatio(1f)
    )
}

@Preview
@Composable
fun TestAspectRatioPreview() {
    Surface(modifier = Modifier.size(height = 500.dp, width = 300.dp)) {
        TestAspectRatio()
    }
}
