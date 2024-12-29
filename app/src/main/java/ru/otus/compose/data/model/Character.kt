package ru.otus.compose.data.model

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val comicCollectionId: String?,
    val comicNames: List<String>,
)
