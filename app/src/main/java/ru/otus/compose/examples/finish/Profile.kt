package ru.otus.compose.examples.finish

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

@Composable
fun Profile() {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
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
                text = "Erwin Schroedinger",
                modifier = Modifier
                    .padding(top = 4.dp),
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "4 January 1961, Vienna",
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
        Profile()
    }
}

