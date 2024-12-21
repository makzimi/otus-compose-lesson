package ru.otus.compose.examples.modifier.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TestPaddingFromBaseline(modifier: Modifier = Modifier) {
    Text(
        text = "Baseline Padding",
        modifier = Modifier
            .background(color = Color.Red)
    )
}

@Preview
@Composable
fun TestFromBaselinePreview() {
    Surface(modifier = Modifier.size(200.dp)) {
        TestPaddingFromBaseline(
            modifier = Modifier
        )
    }
}
