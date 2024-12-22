package ru.otus.compose.examples.remember.finish

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
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RememberSaveableExample() {
    val cardState = rememberProgressState()

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

@Composable
fun rememberProgressState(): CardState =
    rememberSaveable(saver = CardState.Saver) {
        CardState(cardName = "Write Compose Code")
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

    companion object {
        val Saver: Saver<CardState, *> = listSaver(
            save = { listOf<Any>(it.cardName, it.progress) },
            restore = { list ->
                CardState(
                    cardName = list[0].toString(),
                    initialProgress = list[1] as ProgressState
                )
            }
        )
    }
}

@Preview
@Composable
fun RememberSaveableExamplePreview() {
    Surface {
        RememberSaveableExample()
    }
}



