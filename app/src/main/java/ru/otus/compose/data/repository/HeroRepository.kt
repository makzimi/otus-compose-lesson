package ru.otus.compose.data.repository

import ru.otus.compose.data.HeroService
import ru.otus.compose.data.GreatResult
import ru.otus.compose.data.dto.HeroInfoDto
import ru.otus.compose.data.dto.HeroesResponseDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroRepository @Inject constructor(
    private val heroService: HeroService
) {

    suspend fun loadHeroes(offset: Int, limit: Int): HeroesResponseDto {
        return heroService.getHeroes(offset = offset, limit = limit)
    }

    suspend fun loadHeroInfoById(heroId: Long): GreatResult<HeroInfoDto> {
        return GreatResult.Success(
            heroService.getHeroInfo(heroId = heroId).info.results.first()
        )
    }
}