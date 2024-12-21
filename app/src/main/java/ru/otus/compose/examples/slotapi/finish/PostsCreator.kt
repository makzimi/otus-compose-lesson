package ru.otus.compose.examples.slotapi.finish

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import ru.otus.compose.R

object PostsCreator {
    @Composable
    fun create(): ImmutableList<PostState> {
        return listOf(
            PostWithImageState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postImage = R.drawable.cat
            ),
            PostWithTextState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postText = stringResource(R.string.post_text),
            ),
            PostWithTextState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postText = stringResource(R.string.post_text2),
            ),
            PostWithImageState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postImage = R.drawable.cat2
            ),
            PostWithTextState(
                profileState = ProfileState(
                    userImage = R.drawable.profile,
                    userName = stringResource(R.string.profile_name),
                    postDate = stringResource(R.string.post_date)
                ),
                postText = stringResource(R.string.post_text3),
            ),
        ).toPersistentList()
    }
}