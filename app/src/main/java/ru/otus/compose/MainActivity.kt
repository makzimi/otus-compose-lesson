package ru.otus.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint
import design.andromedacompose.components.reveal.CircularReveal

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isLightTheme by remember { mutableStateOf(true) }
            CircularReveal(
                targetState = isLightTheme,
                animationSpec = tween(500)
            ) {
                ComposeLessonApp(
                    darkTheme = !isLightTheme,
                    onToggleTheme = {
                        isLightTheme = !isLightTheme
                    },
                )
            }
        }
    }
}
