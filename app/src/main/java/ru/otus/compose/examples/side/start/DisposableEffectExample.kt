package ru.otus.compose.examples.side.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DisposableEffectExample() {
    var showTimer by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(20.dp)) {
        Row(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(
                text = "Show the timer:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Switch(
                checked = showTimer,
                onCheckedChange = { showTimer = it },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp)
            )
        }

        // Show timer here
    }
}

@Preview
@Composable
fun DisposableEffectExamplePreview() {
    Surface {
        DisposableEffectExample()
    }
}