package ru.otus.compose.features.comicdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import ru.otus.compose.R
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingView
import ru.otus.compose.ui.theme.AppTheme

@Composable
fun ComicDetailsScreen(
    comicsId: String,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ComicDetailsViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            ComicDetailInfoTopBar(
                navHostController = navHostController,
                modifier.fillMaxWidth(),
            )
        },
        content = { innerPadding ->
            ComicDetailInfo(
                comicsId = comicsId,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComicDetailInfoTopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
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
private fun ComicDetailInfo(
    comicsId: String,
    modifier: Modifier = Modifier,
    viewModel: ComicDetailsViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val state = remember { mutableStateOf<ComicDetailsState>(ComicDetailsState.Loading) }

    LaunchedEffect(Unit) {
        state.value = viewModel.fetchComicDetails(comicsId)
    }

    SwipeRefresh(
        modifier = modifier,
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                swipeRefreshState.isRefreshing = true
                state.value = viewModel.fetchComicDetails(comicsId)
                swipeRefreshState.isRefreshing = false
            }
        }
    ) {
        when (val comicDetailsState = state.value) {
            is ComicDetailsState.Loading -> LoadingView(modifier = Modifier.fillMaxSize())
            is ComicDetailsState.Data -> ComicsInfoContent(
                state = comicDetailsState,
            )

            is ComicDetailsState.Error -> ErrorItem(
                message = comicDetailsState.throwable.message.toString(),
                modifier = Modifier.fillMaxSize()
            ) {
                coroutineScope.launch {
                    state.value = ComicDetailsState.Loading
                    state.value = viewModel.fetchComicDetails(comicsId)
                }
            }
        }
    }
}

@Composable
fun ComicsInfoContent(
    state: ComicDetailsState.Data,
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
        BigComicsInfo(state = state)
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
                previewPlaceholder = R.drawable.ic_magazine,
            ),
            contentDescription = stringResource(R.string.hero_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BigComicsInfo(state: ComicDetailsState.Data) {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = state.title,
            color = AppTheme.colors.text,
            style = AppTheme.typography.h3
        )
        if (state.description != null) {
            Text(
                text = state.description,
                color = AppTheme.colors.text,
                style = AppTheme.typography.textMediumBold
            )
        }
    }
}
