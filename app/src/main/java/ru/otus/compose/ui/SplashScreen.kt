package ru.otus.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ru.otus.compose.R

@Composable
fun SplashScreen(
    navHostController: NavHostController,
) {
    SplashContent()
    LaunchedEffect(true) {
        delay(500)
        navHostController.navigate("characters") {
            popUpTo("splash") { inclusive = true }
        }
    }
}

@Composable
fun SplashContent() {
    val image: Painter = painterResource(id = R.drawable.marvel_logo)
    Image(
        painter = image,
        modifier = Modifier
            .fillMaxSize(),
        contentDescription = null
    )
}

