package ru.otus.compose.data.repository

import ru.otus.compose.common.runSuspendCatching
import ru.otus.compose.data.MarvelService
import ru.otus.compose.data.model.Comic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicsRepository @Inject constructor(
    private val service: MarvelService,
    private val comicMapper: ComicMapper,
) {
    suspend fun loadComicDetails(comicId: String): Result<Comic> = runSuspendCatching {
        val comicDto = service.getComicDetails(comicId).data.results.first()
        comicMapper.map(comicDto)
    }

    suspend fun loadComicsForCharacter(
        characterId: String,
    ): Result<List<Comic>> = runSuspendCatching {
        service.getComicsForCharacter(characterId).data.results.map(comicMapper::map)
    }
}
