package ru.otus.compose.examples.layout.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GrayText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(text = text, modifier = modifier.background(color = Color.Gray))
}

@Preview
@Composable
fun IntrinsicPreview() {
    Surface {
        Column {
            GrayText(text = "This")
            GrayText(text = "is")
            GrayText(text = "Intrinsic")
            GrayText(text = "Example")
            GrayText(text = "This is so much fun!")
        }
    }
}
