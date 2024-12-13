package ru.otus.compose.features.characterdetails

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.compose.common.resolve
import ru.otus.compose.data.model.Character
import ru.otus.compose.data.repository.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private var charactersRepository: CharactersRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchCharacter(characterId: Long): CharacterState {
        val result = charactersRepository.loadCharacterById(characterId)

        return result.resolve(
            onSuccess = { character -> character.toState() },
            onError = { throwable -> CharacterState.Error(throwable) }
        )
    }

    private fun Character.toState(): CharacterState.Data {
        return CharacterState.Data(
            id = id,
            name = name,
            description = description,
            comicCollectionId = comicCollectionId,
            imageUrl = imageUrl,
            navigationLink = "character/${id}",
        )
    }
}
