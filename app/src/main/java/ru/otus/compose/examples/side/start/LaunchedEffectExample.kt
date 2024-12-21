package ru.otus.compose.examples.side.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.compose.R

data class CatsState(
    val images: List<Int>,
)

@Composable
fun LaunchedEffectExample(
    state: CatsState
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
    ) { 2 }

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.size(400.dp),
            contentPadding = PaddingValues(horizontal = 40.dp)
        ) { page ->
            Image(
                painter = painterResource(id = state.images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(300.dp)
            )
        }

        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Left")
            }
            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Right")
            }
        }
    }
}


@Preview
@Composable
fun LaunchedEffectExamplePreview() {
    Surface {
        LaunchedEffectExample(
            state = CatsState(
                images = listOf(
                    R.drawable.cat,
                    R.drawable.cat2,
                ),
            )
        )
    }
}