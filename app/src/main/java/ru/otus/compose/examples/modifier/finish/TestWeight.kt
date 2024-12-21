package ru.otus.compose.examples.modifier.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TestWeight(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .background(Color.Yellow)
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(2f)
            .background(Color.Red)
        )
    }
}

@Preview
@Composable
fun TestWeightPreview() {
    Surface(modifier = Modifier.size(300.dp)) {
        TestWeight()
    }
}
