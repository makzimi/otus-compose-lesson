package ru.otus.compose.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSizes internal constructor(
    val smaller: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val larger: Dp = 64.dp,

    val bottomNavigationHeight: Dp = 56.dp,
    val appBarHeight: Dp = 56.dp
)