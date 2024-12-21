package ru.otus.compose.examples.side.finish

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SideEffectExample() {
    SideEffect {
        println("SideEffect executed!")
    }

    Inner()
}

@Composable
fun Inner() {
    Box {
        var count by remember { mutableIntStateOf(0) }

        Column {
            Text(text = "Count: $count")
            Button(onClick = { count++ }) {
                Text("Increment")
            }
        }
    }
}

@Preview
@Composable
fun SideEffectExamplePreview() {
    Surface {
        SideEffectExample()
    }
}