package ru.otus.compose.features.heroes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ru.otus.compose.ThemeToggle
import ru.otus.compose.ui.common.BrightnessMedium
import ru.otus.compose.ui.common.DataViewItem
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingItem
import ru.otus.compose.ui.common.LoadingView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.otus.compose.data.dto.heroinfo.HeroResponse
import ru.otus.compose.ui.theme.AppTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import ru.otus.compose.data.dto.heroinfo.toDataView

@Composable
fun HeroesListScreen(
    navHostController: NavHostController,
    heroesViewModel: HeroesViewModel = hiltViewModel(),
    onToggleTheme: ThemeToggle,
) {
    HeroesListContent(
        navHostController = navHostController,
        heroesViewModel = heroesViewModel,
        onToggleTheme
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroesListContent(
    navHostController: NavHostController,
    heroesViewModel: HeroesViewModel,
    onToggleTheme: ThemeToggle,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = AppTheme.colors.toolbar),
                title = {
                    Text(
                        text = "Marvel heroes",
                        style = AppTheme.typography.textMediumBold,
                        color = AppTheme.colors.text
                    )
                },
                actions = {
                    IconButton(onClick = onToggleTheme) {
                        Icon(
                            modifier = Modifier.size(20.dp, 32.dp),
                            imageVector = Icons.Rounded.BrightnessMedium,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Surface(
                color = AppTheme.colors.background,
            ) {
                val lazyMovieItems: LazyPagingItems<HeroResponse> =
                    heroesViewModel.heroes.collectAsLazyPagingItems()
                val swipeRefreshState = rememberSwipeRefreshState(false)
                val context = LocalContext.current

                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = { lazyMovieItems.refresh() }
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = innerPadding.calculateTopPadding())
                    ) {
                        items(count = lazyMovieItems.itemCount) { index ->
                            val hero = lazyMovieItems[index]
                            DataViewItem(
                                dataViewState = hero!!.toDataView("hero/${hero.id}"),
                                navHostController = navHostController
                            )
                        }

                        lazyMovieItems.apply {
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    if (swipeRefreshState.isSwipeInProgress) {
                                        swipeRefreshState.isRefreshing = false
                                    } else {
                                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                                    }
                                }

                                loadState.append is LoadState.Loading -> {
                                    item { LoadingItem() }
                                }

                                loadState.refresh is LoadState.Error -> {
                                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                                    if (lazyMovieItems.itemCount > 0) {
                                        Toast.makeText(
                                            context,
                                            e.error.localizedMessage,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    item {
                                        if (lazyMovieItems.itemCount == 0) {
                                            ErrorItem(
                                                message = e.error.localizedMessage!!,
                                                modifier = Modifier.fillParentMaxSize(),
                                                onClickRetry = { retry() }
                                            )
                                        }
                                    }
                                }

                                loadState.append is LoadState.Error -> {
                                    val e = lazyMovieItems.loadState.append as LoadState.Error
                                    item {
                                        ErrorItem(
                                            message = e.error.localizedMessage!!,
                                            onClickRetry = { retry() }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
