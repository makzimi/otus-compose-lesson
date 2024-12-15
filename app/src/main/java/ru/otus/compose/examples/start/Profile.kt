package ru.otus.compose.examples.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Composable
fun Profile() {
    Row {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
        )
        Column{
            Text(text = stringResource(R.string.profile_name))
            Text(text = stringResource(R.string.post_date))
        }
    }
}

@Preview
@Composable
private fun ProfilePreview() {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {
        Profile()
    }
}
