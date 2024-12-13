package ru.otus.compose.features.characters

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ru.otus.compose.OnThemeToggle
import ru.otus.compose.ui.common.BrightnessMedium
import ru.otus.compose.ui.common.CommonItem
import ru.otus.compose.ui.common.ErrorItem
import ru.otus.compose.ui.common.LoadingItem
import ru.otus.compose.ui.common.LoadingView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.otus.compose.data.model.Character
import ru.otus.compose.ui.theme.AppTheme
import ru.otus.compose.ui.common.CommonItemState

@Composable
fun CharactersScreen(
    navHostController: NavHostController,
    onToggleTheme: OnThemeToggle,
    charactersViewModel: CharactersViewModel = hiltViewModel(),
) {
    CharactersContent(
        navHostController = navHostController,
        charactersViewModel = charactersViewModel,
        onToggleTheme = onToggleTheme,
        modifier = Modifier.fillMaxSize(),
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun CharactersContent(
    navHostController: NavHostController,
    charactersViewModel: CharactersViewModel,
    onToggleTheme: OnThemeToggle,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { CharactersTopBar(onToggleTheme = onToggleTheme) },
        content = { innerPadding ->
            CharacterList(
                navHostController = navHostController,
                viewModel = charactersViewModel,
                modifier = Modifier.padding(innerPadding),
            )
        },
        containerColor = AppTheme.colors.background,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharactersTopBar(
    onToggleTheme: OnThemeToggle,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = topAppBarColors(containerColor = AppTheme.colors.toolbar),
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
}

@Composable
private fun CharacterList(
    navHostController: NavHostController,
    viewModel: CharactersViewModel,
    modifier: Modifier = Modifier,
) {
    val lazyItems: LazyPagingItems<Character> =
        viewModel.characters.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val context = LocalContext.current

    SwipeRefresh(
        modifier = modifier,
        state = swipeRefreshState,
        onRefresh = { lazyItems.refresh() }
    ) {
        LazyColumn {
            items(count = lazyItems.itemCount) { index ->
                val character = lazyItems[index]
                CommonItem(
                    state = character!!.toCommonItemState(),
                    navHostController = navHostController
                )
            }

            with(lazyItems) {
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
                        val e = lazyItems.loadState.refresh as LoadState.Error
                        if (lazyItems.itemCount > 0) {
                            Toast.makeText(
                                context,
                                e.error.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        item {
                            if (lazyItems.itemCount == 0) {
                                ErrorItem(
                                    message = e.error.localizedMessage!!,
                                    modifier = Modifier.fillParentMaxSize(),
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = lazyItems.loadState.append as LoadState.Error
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

private fun Character.toCommonItemState(): CommonItemState {
    return CommonItemState(
        title = name,
        imageUrl = imageUrl,
        navigationLink = "character/$id"
    )
}