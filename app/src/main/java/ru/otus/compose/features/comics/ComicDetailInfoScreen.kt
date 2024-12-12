package ru.otus.compose.features.comics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.otus.compose.features.herodetails.BigHeroImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import ru.otus.compose.data.dto.GreatResult
import ru.otus.compose.data.dto.comicsinfo.ComicsDto
import ru.otus.compose.data.dto.getImageUrl
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingView
import ru.otus.compose.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicDetailInfoScreen(
    navHostController: NavHostController,
    viewModel: ComicsViewModel = hiltViewModel(),
    comicsId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
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
                //backgroundColor = AppTheme.colors.toolbar
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            val coroutineScope = rememberCoroutineScope()
            val swipeRefreshState = rememberSwipeRefreshState(false)
            val comicsInfo =
                remember { mutableStateOf<GreatResult<ComicsDto>>(GreatResult.Progress) }

            LaunchedEffect(Unit) {
                val info = viewModel.fetchComicDetailInfo(comicsId)
                comicsInfo.value = info
            }

            SwipeRefresh(
                modifier = modifier,
                state = swipeRefreshState,
                onRefresh = {
                    coroutineScope.launch {
                        swipeRefreshState.isRefreshing = true
                        comicsInfo.value = viewModel.fetchComicDetailInfo(comicsId)
                        swipeRefreshState.isRefreshing = false
                    }
                }
            ) {
                when (val comicsInfoDto = comicsInfo.value) {
                    is GreatResult.Progress -> LoadingView(modifier = Modifier.fillMaxSize())
                    is GreatResult.Success -> ComicsInfoContent(
                        comicsDto = comicsInfoDto.data,
                        navHostController = navHostController
                    )
                    is GreatResult.Error -> ErrorItem(
                        message = comicsInfoDto.exception.message.toString(),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        coroutineScope.launch {
                            comicsInfo.value = GreatResult.Progress
                            comicsInfo.value = viewModel.fetchComicDetailInfo(comicsId)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ComicsInfoContent(
    comicsDto: ComicsDto,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BigHeroImage(url = comicsDto.thumbnail.getImageUrl())
        Spacer(modifier = Modifier.height(16.dp))
        BigComicsInfo(comicsInfo = comicsDto)
    }
}

@Composable
fun BigComicsInfo(comicsInfo: ComicsDto) {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = comicsInfo.title,
            color = AppTheme.colors.text,
            style = AppTheme.typography.h3
        )
        if (comicsInfo.description != null) {
            Text(
                text = comicsInfo.description,
                color = AppTheme.colors.text,
                style = AppTheme.typography.textMediumBold
            )
        }
    }
}