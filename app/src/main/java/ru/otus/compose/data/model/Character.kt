package ru.otus.compose.data.model

import ru.otus.compose.ui.common.DataViewState

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val comicCollectionId: String?,
)

fun Character.toState(navigationLink: String): DataViewState {
    return DataViewState(
        title = name,
        imageUrl = imageUrl,
        navigationLink = navigationLink
    )
}