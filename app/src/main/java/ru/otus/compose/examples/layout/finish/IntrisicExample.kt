package ru.otus.compose.examples.layout.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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

@Preview
@Composable
fun IntrinsicPreview2() {
    Surface {
        Column {
            GrayText(text = "This", Modifier.fillMaxWidth())
            GrayText(text = "is", Modifier.fillMaxWidth())
            GrayText(text = "Intrinsic", Modifier.fillMaxWidth())
            GrayText(text = "Example", Modifier.fillMaxWidth())
            GrayText(text = "This is so much fun!", Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun IntrinsicPreview3() {
    Surface {
        Column(Modifier.width(IntrinsicSize.Max)) {
            GrayText(text = "This", Modifier.fillMaxWidth())
            GrayText(text = "is", Modifier.fillMaxWidth())
            GrayText(text = "Intrinsic", Modifier.fillMaxWidth())
            GrayText(text = "Example", Modifier.fillMaxWidth())
            GrayText(text = "This is so much fun!", Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun IntrinsicPreview4() {
    Surface {
        Column(Modifier.width(IntrinsicSize.Min)) {
            GrayText(text = "This", Modifier.fillMaxWidth())
            GrayText(text = "is", Modifier.fillMaxWidth())
            GrayText(text = "Intrinsic", Modifier.fillMaxWidth())
            GrayText(text = "Example", Modifier.fillMaxWidth())
            GrayText(text = "This is so much fun!", Modifier.fillMaxWidth())
        }
    }
}
