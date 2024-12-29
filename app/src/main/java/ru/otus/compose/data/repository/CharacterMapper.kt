package ru.otus.compose.data.repository

import androidx.core.net.toUri
import ru.otus.compose.data.dto.CharacterDto
import ru.otus.compose.data.dto.getImageUrl
import ru.otus.compose.data.model.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor() {
    fun map(characterDto: CharacterDto): Character {
        return Character(
            id = characterDto.id,
            name = characterDto.name,
            description = characterDto.description,
            comicCollectionId = characterDto.comics.collectionURI.toUri().path
                ?.split("/")
                ?.get(4),
            imageUrl = characterDto.thumbnail.getImageUrl(),
            comicNames = characterDto.comics.items.map{ it.name }
        )
    }
}
