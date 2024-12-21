package ru.otus.compose.examples.slotapi.finish

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class FeedState(
    val posts: ImmutableList<PostState>
)

@Composable
fun Feed(
    state: FeedState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(state.posts) { item ->
            when (item) {
                is PostWithTextState -> PostWithText(postWithTextState = item)
                is PostWithImageState -> PostWithImage(state = item)
            }
        }
    }
}

@Preview
@Composable
fun FeedPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Feed(
            state = FeedState(posts = PostsCreator.create()),
        )
    }
}
