package ru.otus.compose.data.repository

import ru.otus.compose.common.runSuspendCatching
import ru.otus.compose.data.MarvelService
import ru.otus.compose.data.model.Character
import ru.otus.compose.data.model.PagedCharacters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepository @Inject constructor(
    private val marvelService: MarvelService,
    private val characterMapper: CharacterMapper,
) {
    suspend fun loadCharacters(
        offset: Int,
        limit: Int,
    ): Result<PagedCharacters> = runSuspendCatching {
        val characterDataWrapperDto = marvelService.getCharacters(offset = offset, limit = limit)

        val characters = characterDataWrapperDto.data.results.map(characterMapper::map)

        PagedCharacters(
            characters = characters,
            offset = characterDataWrapperDto.data.offset,
        )
    }

    suspend fun loadCharacterById(characterId: Long): Result<Character> = runSuspendCatching {
        val characterDto = marvelService.getCharacterDetails(characterId = characterId)
            .data
            .results
            .first()

        characterMapper.map(characterDto)
    }
}
