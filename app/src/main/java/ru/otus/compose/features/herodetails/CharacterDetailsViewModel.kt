package ru.otus.compose.features.herodetails

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.compose.data.model.Character
import ru.otus.compose.data.repository.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel
@Inject constructor(
    private var charactersRepository: CharactersRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchCharacter(characterId: Long): Result<Character> {
        return charactersRepository.loadCharacterById(characterId)
    }
}
