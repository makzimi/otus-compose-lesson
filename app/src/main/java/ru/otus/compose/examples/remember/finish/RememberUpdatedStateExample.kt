package ru.otus.compose.examples.remember.finish

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import ru.otus.compose.R

private val SplashWaitTime = 2000L

@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(onTimeout) {
            delay(SplashWaitTime)
            onTimeout()
        }
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = null
        )
    }
}

@Composable
fun LandingScreenFixed(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }

        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = null
        )
    }
}
