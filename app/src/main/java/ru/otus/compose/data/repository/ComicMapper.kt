package ru.otus.compose.data.repository

import ru.otus.compose.data.dto.ComicDto
import ru.otus.compose.data.dto.getImageUrl
import ru.otus.compose.data.model.Comic
import javax.inject.Inject

class ComicMapper @Inject constructor() {
    fun map(comicDto: ComicDto): Comic {
        return Comic(
            id = comicDto.id,
            title = comicDto.title,
            imageUrl = comicDto.thumbnail.getImageUrl(),
            description = comicDto.description,
        )
    }
}
