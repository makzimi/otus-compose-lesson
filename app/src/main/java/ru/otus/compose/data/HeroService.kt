package ru.otus.compose.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.otus.compose.data.dto.ComicsResponseDto
import ru.otus.compose.data.dto.HeroInfoResponseDto
import ru.otus.compose.data.dto.HeroesResponseDto

interface HeroService {
    @GET("v1/public/characters")
    suspend fun getHeroes(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): HeroesResponseDto

    @GET("v1/public/characters/{characterId}")
    suspend fun getHeroInfo(@Path("characterId") heroId: Long): HeroInfoResponseDto

    @GET("v1/public/characters/{comicsId}/comics")
    suspend fun getComicsInfoById(@Path("comicsId") comicsId: String): ComicsResponseDto

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicsDetailInfo(@Path("comicId") comicsId: String): ComicsResponseDto
}
