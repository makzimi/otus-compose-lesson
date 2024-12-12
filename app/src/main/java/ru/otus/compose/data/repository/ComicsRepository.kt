package ru.otus.compose.data.repository

import ru.otus.compose.data.HeroService
import ru.otus.compose.data.GreatResult
import ru.otus.compose.data.dto.comicsinfo.ComicsDto
import ru.otus.compose.data.dto.ComicsWrapperDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicsRepository @Inject constructor(
    private val service: HeroService
) {

    suspend fun loadComicDetailInfo(id: String): GreatResult<ComicsDto> {
        return GreatResult.Success(
            service.getComicsDetailInfo(id).info.results.first()
        )
    }

    suspend fun loadComicsById(id: String): GreatResult<ComicsWrapperDto> {
        return GreatResult.Success(service.getComicsInfoById(id).info)
    }
}