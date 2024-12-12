package ru.otus.compose.features.comics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import ru.otus.compose.data.dto.GreatResult
import ru.otus.compose.data.dto.comicsinfo.ComicsWrapperDto
import ru.otus.compose.ui.common.DataViewItem
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingView
import ru.otus.compose.ui.theme.AppTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import ru.otus.compose.data.dto.comicsinfo.toDataViewState

@Composable
fun ComicsScreen(
    navHostController: NavHostController,
    viewModel: ComicsViewModel = hiltViewModel(),
    comicsId: String
) {
    ComicsListContent(
        navHostController = navHostController,
        viewModel = viewModel,
        comicsId = comicsId
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicsListContent(
    navHostController: NavHostController,
    viewModel: ComicsViewModel,
    comicsId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
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
                            imageVector = Icons.Filled.ArrowBack,
                            tint = AppTheme.colors.iconColor,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(color = AppTheme.colors.background) {

            val modifier = Modifier.padding(innerPadding)
            val coroutineScope = rememberCoroutineScope()
            val swipeRefreshState = rememberSwipeRefreshState(false)
            val comicsInfo =
                remember { mutableStateOf<GreatResult<ComicsWrapperDto>>(GreatResult.Progress) }

            LaunchedEffect(true) {
                val info = viewModel.fetchComicsInfoById(comicsId)
                comicsInfo.value = info
            }

            SwipeRefresh(
                modifier = modifier,
                state = swipeRefreshState,
                onRefresh = {
                    coroutineScope.launch {
                        swipeRefreshState.isRefreshing = true
                        comicsInfo.value = viewModel.fetchComicsInfoById(comicsId)
                        swipeRefreshState.isRefreshing = false
                    }
                }
            ) {
                when (val comicsInfoRequest = comicsInfo.value) {
                    is GreatResult.Progress -> {
                        LoadingView(modifier = Modifier.fillMaxSize())
                    }
                    is GreatResult.Success -> Comics(
                        comicsWrapper = comicsInfoRequest.data,
                        navHostController = navHostController
                    )
                    is GreatResult.Error -> {
                        ErrorItem(
                            message = comicsInfoRequest.exception.message.toString(),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            coroutineScope.launch {
                                comicsInfo.value = GreatResult.Progress
                                comicsInfo.value = viewModel.fetchComicsInfoById(comicsId)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Comics(
    comicsWrapper: ComicsWrapperDto,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(comicsWrapper.results) { comics ->
            DataViewItem(
                navHostController = navHostController,
                dataViewState = comics.toDataViewState("comicInfo/${comics.id}")
            )
        }
    }
}
