package ru.otus.compose.features.comicdetails

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ComicDetailsState {
    @Immutable
    data object Loading: ComicDetailsState

    @Immutable
    data class Error(val throwable: Throwable): ComicDetailsState

    @Immutable
    data class Data(
        val id: String,
        val title: String,
        val description: String?,
        val imageUrl: String,
    ): ComicDetailsState
}
