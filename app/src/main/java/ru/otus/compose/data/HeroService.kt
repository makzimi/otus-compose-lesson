package ru.otus.compose.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.otus.compose.data.dto.comicsinfo.ComicsWrapperResponse
import ru.otus.compose.data.dto.heroinfo.HeroInfoResponse
import ru.otus.compose.data.dto.heroinfo.HeroesResponseDto

interface HeroService {
    @GET("v1/public/characters")
    suspend fun getHeroes(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): HeroesResponseDto

    @GET("v1/public/characters/{characterId}")
    suspend fun getHeroInfo(@Path("characterId") heroId: Long): HeroInfoResponse

    @GET("v1/public/characters/{comicsId}/comics")
    suspend fun getComicsInfoById(@Path("comicsId") comicsId: String): ComicsWrapperResponse

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicsDetailInfo(@Path("comicId") comicsId: String): ComicsWrapperResponse
}
