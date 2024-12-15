package ru.otus.compose.examples.finish

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.otus.compose.R

@Immutable
data class PostWithTextState(
    val profileState: ProfileState,
    val postText: String
)

@Composable
fun PostWithText(
    postWithTextState: PostWithTextState,
    modifier: Modifier = Modifier,
) {
    Post(
        userSection = {
            Profile(
                profileState = postWithTextState.profileState
            )
        },
        postSection = {
            Text(
                text = postWithTextState.postText,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        buttonsSection = { BottomButtons() },
        modifier = modifier,
    )
}

@Preview
@Composable
fun PostPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        PostWithText(
            postWithTextState = PostWithTextState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postText = stringResource(R.string.post_text),
            )
        )
    }
}