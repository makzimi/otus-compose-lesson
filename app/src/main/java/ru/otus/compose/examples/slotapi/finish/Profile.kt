package ru.otus.compose.examples.slotapi.finish

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Immutable
data class ProfileState(
    val userImage: Int,
    val userName: String,
    val postDate: String,
)

@Composable
fun Profile(
    profileState: ProfileState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = profileState.userImage),
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = profileState.userName,
                modifier = Modifier
                    .padding(top = 4.dp),
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = profileState.postDate,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {
        Profile(
            profileState = ProfileState(
                userImage = R.drawable.profile,
                userName = stringResource(R.string.profile_name),
                postDate = stringResource(R.string.post_date),
            ),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp)),
        )
    }
}

