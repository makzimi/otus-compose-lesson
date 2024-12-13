package ru.otus.compose.features.comics

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ComicsState {

    @Immutable
    data object Loading : ComicsState

    @Immutable
    data class Error(val throwable: Throwable) : ComicsState

    @Immutable
    data class Data(val comics: List<ComicState>) : ComicsState {
        data class ComicState(
            val id: String,
            val title: String,
            val description: String?,
            val imageUrl: String,
        )
    }
}
