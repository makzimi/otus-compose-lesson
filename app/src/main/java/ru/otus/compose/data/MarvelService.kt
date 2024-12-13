package ru.otus.compose.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.otus.compose.data.dto.ComicDataWrapperDto
import ru.otus.compose.data.dto.CharacterDataWrapperDto

interface MarvelService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): CharacterDataWrapperDto

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Long): CharacterDataWrapperDto

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComicsForCharacter(@Path("characterId") characterId: String): ComicDataWrapperDto

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicDetails(@Path("comicId") comicsId: String): ComicDataWrapperDto
}
