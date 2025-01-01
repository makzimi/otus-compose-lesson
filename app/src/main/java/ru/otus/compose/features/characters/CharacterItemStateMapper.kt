package ru.otus.compose.features.characters

import kotlinx.collections.immutable.toPersistentList
import ru.otus.compose.data.model.Character
import javax.inject.Inject

class CharacterItemStateMapper @Inject constructor() {
    fun map(character: Character) : CharacterItemState {
        return CharacterItemState(
            id = character.id.toString(),
            title = character.name,
            description = character.description,
            imageUrl = character.imageUrl,
            comicNames = character.comicNames
                .take(3)
                .toPersistentList(),
            navigationDestination = ru.otus.compose.Character(character.id, character.imageUrl),
        )
    }
}
