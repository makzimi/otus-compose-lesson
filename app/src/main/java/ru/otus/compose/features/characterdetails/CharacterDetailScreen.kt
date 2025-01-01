package ru.otus.compose.features.characterdetails

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import ru.otus.compose.ui.theme.AppTheme

private const val COMIC_IMAGE = "https://static.wikia.nocookie.net/character-power/images/8/8f/DC_Comics.png/revision/latest/scale-to-width-down/700?cb=20190203204448&path-prefix=ru"

@Immutable
data class SharedCharacterInfo(
    val characterId: Long,
    val imageUrl: String,
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterDetailScreen(
    navHostController: NavHostController,
    sharedCharacterInfo: SharedCharacterInfo,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
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
                sharedCharacterInfo = sharedCharacterInfo,
                navHostController = navHostController,
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding),
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun CharacterDetailsContent(
    sharedCharacterInfo: SharedCharacterInfo,
    navHostController: NavHostController,
    viewModel: CharacterDetailsViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val state: State<CharacterState> = getState(viewModel, sharedCharacterInfo.characterId)

    SwipeRefresh(
        modifier = modifier,
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                swipeRefreshState.isRefreshing = true
                state.unsafeMutable().value =
                    viewModel.fetchCharacter(sharedCharacterInfo.characterId)
                swipeRefreshState.isRefreshing = false
            }
        }
    ) {
        when (val characterState = state.value) {
            is CharacterState.Data, is CharacterState.Loading -> CharacterContent(
                state = characterState,
                sharedCharacterInfo = sharedCharacterInfo,
                navHostController = navHostController,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
            )

            is CharacterState.Error -> ErrorItem(
                message = characterState.throwable.message.toString(),
                modifier = Modifier.fillMaxSize()
            ) {
                coroutineScope.launch {
                    state.unsafeMutable().value = CharacterState.Loading
                    state.unsafeMutable().value =
                        viewModel.fetchCharacter(sharedCharacterInfo.characterId)
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun CharacterContent(
    state: CharacterState,
    sharedCharacterInfo: SharedCharacterInfo,
    navHostController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BigImage(
            sharedCharacterInfo = sharedCharacterInfo,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CharacterDescription(state = state)
        ComicsItem(navHostController, state)
    }
}

@Composable
private fun ComicsItem(
    navHostController: NavHostController,
    state: CharacterState
) {
    if (state is CharacterState.Data) {
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
                    request = COMIC_IMAGE,
                    previewPlaceholder = R.drawable.ic_magazine,
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
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun BigImage(
    sharedCharacterInfo: SharedCharacterInfo,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    with(sharedTransitionScope) {
        Image(
            painter = rememberGlidePainter(
                request = sharedCharacterInfo.imageUrl,
            ),
            contentDescription = stringResource(R.string.hero_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .sharedElement(
                    sharedTransitionScope.rememberSharedContentState(
                        key = "image-${sharedCharacterInfo.characterId}"
                    ),
                    animatedVisibilityScope = animatedContentScope
                )
                .fillMaxWidth()
                .height(240.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
        )
    }
}

@Composable
private fun CharacterDescription(state: CharacterState) {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp)
    ) {
        if (state is CharacterState.Data) {
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
        } else {
            CircularProgressIndicator()
        }
    }
}
