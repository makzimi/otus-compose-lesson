package ru.otus.compose.examples.finish

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Composable
fun Post(
    modifier: Modifier = Modifier,
    userSection: @Composable () -> Unit,
    postSection: @Composable () -> Unit,
    buttonsSection: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            userSection()
            postSection()
            buttonsSection()
        }
    }
}

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
