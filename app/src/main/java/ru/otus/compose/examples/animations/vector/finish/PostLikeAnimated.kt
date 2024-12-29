package ru.otus.compose.examples.animations.vector.finish

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.otus.compose.R
import ru.otus.compose.examples.slotapi.finish.Post
import ru.otus.compose.examples.slotapi.finish.PostButton
import ru.otus.compose.examples.slotapi.finish.PostState
import ru.otus.compose.examples.slotapi.finish.Profile
import ru.otus.compose.examples.slotapi.finish.ProfileState

@Immutable
class PostLikeAnimatedState(
    profileState: ProfileState,
    val postText: String,
    val isLiked: Boolean,
) : PostState(profileState = profileState)

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun PostLikeAnimated(
    state: PostLikeAnimatedState,
    onLiked: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Post(
        userSection = {
            Profile(
                profileState = state.profileState
            )
        },
        postSection = {
            Text(
                text = state.postText,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        buttonsSection = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_like_animated)
                IconButton(onClick = { onLiked(state.isLiked) }) {
                    Icon(
                        painter = rememberAnimatedVectorPainter(
                            animatedImageVector = image,
                            atEnd = state.isLiked
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                PostButton(icon = R.drawable.ic_bookmark)
                PostButton(icon = R.drawable.ic_share)
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun PostLikeAnimatedPreview() {
    var isLiked by remember { mutableStateOf(false) }

    Surface(color = MaterialTheme.colorScheme.background) {
        PostLikeAnimated(
            state = PostLikeAnimatedState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postText = stringResource(R.string.post_text),
                isLiked = isLiked,
            ),
            onLiked = { currentIsLiked ->
                isLiked = !currentIsLiked
            }
        )
    }
}