package ru.otus.compose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography
import ru.otus.compose.R

data class AppTypography internal constructor(
    val paragraph1: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    val h1: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    val h3: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    val h4: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    val h5: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    val h6: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    val bookItemTitle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        lineHeight = 24.sp
    ),
    val bookItemAuthor: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        lineHeight = 24.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.sp,
        lineHeight = 24.sp
    ),
    val body2: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    val materialTypography: Typography = Typography(
        bodyMedium = paragraph1
    ),
    val textMediumBold: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.marvel_regular))
    ),
)