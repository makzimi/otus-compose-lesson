package ru.otus.compose.features.characterdetails

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CharacterState {

    @Immutable
    data object Loading: CharacterState

    @Immutable
    data class Error(val throwable: Throwable): CharacterState

    @Immutable
    data class Data(
        val id: Long,
        val name: String,
        val description: String,
        val imageUrl: String,
        val comicCollectionId: String?,
        val navigationLink: String
    ): CharacterState
}
