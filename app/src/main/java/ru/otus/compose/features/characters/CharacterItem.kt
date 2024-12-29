package ru.otus.compose.features.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import ru.otus.compose.ui.theme.AppTheme
import com.google.accompanist.glide.rememberGlidePainter
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.otus.compose.R
import ru.otus.compose.ui.theme.ComposeLessonTheme

@Immutable
data class CharacterItemState(
    val title: String,
    val description: String,
    val imageUrl: String,
    val comicNames: ImmutableList<String>,
    val navigationDestination: Any,
)

@Composable
fun CharacterItem(
    state: CharacterItemState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors()
            .copy(containerColor = AppTheme.colors.card),
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top,
        ) {
            ItemImage(
                imageUrl = state.imageUrl
            )
            Column(

            ) {
                Text(
                    modifier = Modifier,
                    text = state.title,
                    maxLines = 2,
                    style = AppTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.text
                )
                Text(
                    modifier = Modifier,
                    text = state.description,
                    maxLines = 2,
                    style = AppTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.text
                )
                Column(
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    state.comicNames.forEach { comicName ->
                        Text(
                            modifier = Modifier,
                            text = "• $comicName",
                            maxLines = 1,
                            style = AppTheme.typography.body2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                            color = AppTheme.colors.text
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberGlidePainter(
            request = imageUrl,
            previewPlaceholder = R.drawable.default_image,
        ),
        contentDescription = stringResource(R.string.hero_image_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(80.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Preview
@Composable
fun CharacterItemPreview() {
    ComposeLessonTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
        ) {
            CharacterItem(
                state = CharacterItemState(
                    title = "Deadpool",
                    description = LoremIpsum(words = 10).values.toList().first().toString(),
                    imageUrl = "",
                    comicNames = persistentListOf(
                        "Comic1",
                        "Comic2",
                        "Comic3",
                    ),
                    navigationDestination = Unit,
                ),
                onClick = { },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}