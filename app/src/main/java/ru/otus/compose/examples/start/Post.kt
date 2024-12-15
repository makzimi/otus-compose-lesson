package ru.otus.compose.examples.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.otus.compose.R

@Composable
fun Post(
    postText: String,
    onLikeClicked: () -> Unit = {},
    onBookmarkClicked: () -> Unit = { },
    onShareClicked: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Profile

            Text(
                text = postText,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onLikeClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "Like",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onBookmarkClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Like",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onShareClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PostPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        Post(postText = stringResource(R.string.post_text))
    }
}