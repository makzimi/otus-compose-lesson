package ru.otus.compose.examples.finish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.otus.compose.R

@Composable
fun BottomButtons(
    onLikeClicked: () -> Unit = { },
    onBookmarkClicked: () -> Unit = { },
    onShareClicked: () -> Unit = { },
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        PostButton(
            icon = R.drawable.ic_like,
            onClick = onLikeClicked,
        )
        PostButton(
            icon = R.drawable.ic_bookmark,
            onClick = onBookmarkClicked,
        )
        PostButton(
            icon = R.drawable.ic_share,
            onClick = onShareClicked,
        )
    }
}
