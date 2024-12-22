package ru.otus.compose.features.characterdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import ru.otus.compose.ComicsCollection
import ru.otus.compose.R
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingView
import ru.otus.compose.ui.theme.AppTheme

@Composable
fun CharacterDetailScreen(
    navHostController: NavHostController,
    characterId: Long,
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            CharacterDetailsTopBar(
                navHostController = navHostController,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        content = { innerPadding ->
            CharacterDetailsContent(
                characterId = characterId,
                navHostController = navHostController,
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding),
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailsTopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors = topAppBarColors(containerColor = AppTheme.colors.toolbar),
        title = {
            Text(
                text = "Back to others",
                style = AppTheme.typography.textMediumBold,
                color = AppTheme.colors.text
            )
        },
        navigationIcon = {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = AppTheme.colors.iconColor,
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
private fun CharacterDetailsContent(
    characterId: Long,
    navHostController: NavHostController,
    viewModel: CharacterDetailsViewModel,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val state: State<CharacterState> = getState(viewModel, characterId)

    SwipeRefresh(
        modifier = modifier,
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                swipeRefreshState.isRefreshing = true
                state.unsafeMutable().value = viewModel.fetchCharacter(characterId)
                swipeRefreshState.isRefreshing = false
            }
        }
    ) {
        when (val characterState = state.value) {
            is CharacterState.Loading -> LoadingView(modifier = Modifier.fillMaxSize())
            is CharacterState.Data -> CharacterContent(
                state = characterState,
                navHostController = navHostController
            )

            is CharacterState.Error -> ErrorItem(
                message = characterState.throwable.message.toString(),
                modifier = Modifier.fillMaxSize()
            ) {
                coroutineScope.launch {
                    state.unsafeMutable().value = CharacterState.Loading
                    state.unsafeMutable().value = viewModel.fetchCharacter(characterId)
                }
            }
        }
    }
}

inline fun <reified T> State<T>.unsafeMutable(): MutableState<T> {
    return this as MutableState<T>
}

@Composable
private fun getState(
    viewModel: CharacterDetailsViewModel,
    characterId: Long,
): State<CharacterState> {
    return produceState<CharacterState>(initialValue = CharacterState.Loading) {
        value = viewModel.fetchCharacter(characterId = characterId)
    }
}

@Composable
private fun CharacterContent(
    state: CharacterState.Data,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BigImage(url = state.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        CharacterDescription(state = state)
        ComicsItem(navHostController, state)
    }
}

@Composable
private fun ComicsItem(
    navHostController: NavHostController,
    state: CharacterState.Data
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navHostController.navigate(
                    ComicsCollection(comicsCollectionId = state.comicCollectionId.orEmpty())
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberGlidePainter(
                request = "https://static.wikia.nocookie.net/character-power/images/8/8f/DC_Comics.png/revision/latest/scale-to-width-down/700?cb=20190203204448&path-prefix=ru",
                fadeIn = true,
            ),
            contentDescription = "Comics",
            modifier = Modifier
                .size(
                    height = 64.dp,
                    width = 84.dp
                )
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Watch all comics",
            color = AppTheme.colors.text,
            style = AppTheme.typography.textMediumBold
        )
    }
}

@Composable
private fun BigImage(url: String) {
    Card(
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .background(color = AppTheme.colors.card)
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = rememberGlidePainter(
                request = url,
                fadeIn = true,
                requestBuilder = { placeholder(R.drawable.default_image) }
            ),
            contentDescription = stringResource(R.string.hero_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CharacterDescription(state: CharacterState.Data) {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = state.name,
            color = AppTheme.colors.text,
            style = AppTheme.typography.h3
        )
        Text(
            text = state.description,
            color = AppTheme.colors.text,
            style = AppTheme.typography.textMediumBold
        )
    }
}
