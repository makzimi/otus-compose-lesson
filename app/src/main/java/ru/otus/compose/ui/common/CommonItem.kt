package ru.otus.compose.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.otus.compose.ui.theme.AppTheme
import com.google.accompanist.glide.rememberGlidePainter
import ru.otus.compose.R

@Immutable
data class CommonItemState(
    val title: String,
    val imageUrl: String,
    val navigationDestination: Any,
)

@Composable
fun CommonItem(
    state: CommonItemState,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .background(color = AppTheme.colors.card)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable(onClick = {
                navHostController.navigate(state.navigationDestination)
            })
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ItemTitle(
                title = state.title,
                modifier = Modifier.weight(1f)
            )
            ItemImage(
                imageUrl = state.imageUrl
            )
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
            previewPlaceholder = R.drawable.ic_face,
        ),
        contentDescription = stringResource(R.string.hero_image_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(120.dp)
            .width(120.dp)
            .padding(start = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
private fun ItemTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = AppTheme.typography.textMediumBold,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        color = AppTheme.colors.text
    )
}
