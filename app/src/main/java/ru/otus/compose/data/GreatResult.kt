package ru.otus.compose.data

sealed class GreatResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : GreatResult<T>()
    data class Error(val exception: Exception) : GreatResult<Nothing>()
    data object Progress : GreatResult<Nothing>()
}