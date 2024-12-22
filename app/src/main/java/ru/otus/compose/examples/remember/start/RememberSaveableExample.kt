package ru.otus.compose.examples.remember.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RememberSaveableExample() {
    val cardState = remember { CardState(cardName = "Write Compose Code") }

    Card (modifier = Modifier.padding(20.dp)) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = cardState.cardName,
                style = MaterialTheme.typography.titleLarge
                )
            HorizontalDivider()
            Text(
                text = cardState.progress.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {
                    cardState.progress = ProgressState.InProgress
                }
            )
        }
    }
}


enum class ProgressState {
    Todo,
    InProgress,
    Done,
}

class CardState(
    val cardName: String,
    initialProgress: ProgressState = ProgressState.Todo,
) {
    var progress by mutableStateOf<ProgressState>(initialProgress)
}

@Preview
@Composable
fun RememberSaveableExamplePreview() {
    Surface {
        RememberSaveableExample()
    }
}



