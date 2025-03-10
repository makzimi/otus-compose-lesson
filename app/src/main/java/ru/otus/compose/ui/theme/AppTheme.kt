package ru.otus.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevations(val card: Dp = 0.dp, val default: Dp = 0.dp)

val LocalElevations = compositionLocalOf { Elevations() }

@Composable
fun ExampleProvider() {
    val elevations = if (isSystemInDarkTheme()) {
        Elevations(card = 1.dp, default = 1.dp)
    } else {
        Elevations(card = 0.dp, default = 0.dp)
    }

    // Bind elevation as the value for LocalElevations
    CompositionLocalProvider(LocalElevations provides elevations) {
        LocalElevations.current
    }
}

@Composable
fun ComposeLessonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: AppTypography = AppTypography(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorPalette() else lightColorPalette()
    CompositionLocalProvider(
        LocalColor provides colors,
        LocalTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = colors.materialColors,
            typography = typography.materialTypography,
            content = content
        )
    }
}

object AppTheme {

    val colors: ColorPalette
        @Composable get() = LocalColor.current

    val typography: AppTypography
        @Composable get() = LocalTypography.current

    val sizes: AppSizes
        @Composable get() = AppSizes()
}

internal val LocalColor = staticCompositionLocalOf { lightColorPalette() }
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }