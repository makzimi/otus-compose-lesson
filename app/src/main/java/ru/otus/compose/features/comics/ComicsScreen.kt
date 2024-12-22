package ru.otus.compose.features.comics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import ru.otus.compose.ui.common.CommonItem
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingView
import ru.otus.compose.ui.theme.AppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.foundation.lazy.items
import ru.otus.compose.ComicInfo
import ru.otus.compose.ui.common.CommonItemState

@Composable
fun ComicsScreen(
    navHostController: NavHostController,
    characterId: String,
    viewModel: ComicsViewModel = hiltViewModel(),
) {
    ComicsListContent(
        characterId = characterId,
        navHostController = navHostController,
        viewModel = viewModel,
    )
}

@Composable
private fun ComicsListContent(
    characterId: String,
    navHostController: NavHostController,
    viewModel: ComicsViewModel,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ComicsListTopBar(
                navHostController = navHostController,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        containerColor = AppTheme.colors.background
    ) { innerPadding ->
        ComicsList(
            characterId = characterId,
            navHostController = navHostController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComicsListTopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = topAppBarColors(containerColor = AppTheme.colors.toolbar),
        title = {
            Text(
                text = "Comics",
                style = AppTheme.typography.textMediumBold,
                color = AppTheme.colors.text
            )
        },
        navigationIcon = {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = AppTheme.colors.iconColor,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun ComicsList(
    characterId: String,
    navHostController: NavHostController,
    viewModel: ComicsViewModel,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val state = remember { mutableStateOf<ComicsState>(ComicsState.Loading) }

    LaunchedEffect(true) {
        state.value = viewModel.fetchComicsForCharacter(characterId)
    }

    SwipeRefresh(
        modifier = modifier,
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                swipeRefreshState.isRefreshing = true
                state.value = viewModel.fetchComicsForCharacter(characterId)
                swipeRefreshState.isRefreshing = false
            }
        }
    ) {
        when (val comicsState = state.value) {
            is ComicsState.Loading -> LoadingView(modifier = Modifier.fillMaxSize())

            is ComicsState.Data -> Comics(
                state = comicsState,
                navHostController = navHostController
            )

            is ComicsState.Error -> {
                ErrorItem(
                    message = comicsState.throwable.message.toString(),
                    modifier = Modifier.fillMaxSize()
                ) {
                    coroutineScope.launch {
                        state.value = ComicsState.Loading
                        state.value = viewModel.fetchComicsForCharacter(characterId)
                    }
                }
            }
        }
    }
}

@Composable
fun Comics(
    state: ComicsState.Data,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(state.comics) { comicState ->
            CommonItem(
                navHostController = navHostController,
                state = comicState.toCommonItemState(),
            )
        }
    }
}

private fun ComicsState.Data.ComicState.toCommonItemState(): CommonItemState {
    return CommonItemState(
        title = title,
        imageUrl = imageUrl,
        navigationDestination = ComicInfo(id)
    )
}
