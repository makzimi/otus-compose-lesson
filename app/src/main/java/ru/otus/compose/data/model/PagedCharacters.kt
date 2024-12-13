package ru.otus.compose.data.model

data class PagedCharacters(
    val characters: List<Character>,
    val offset: Int,
)
