package ru.otus.compose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataWrapperDto(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("data")
    val data: CharacterDataContainerDto
)

@Serializable
data class CharacterDataContainerDto(
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("results")
    val results: List<CharacterDto>
)

@Serializable
data class CharacterDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto,
    @SerialName("comics")
    val comics: ComicsListDto,
)

@Serializable
data class ComicsListDto(
    @SerialName("collectionURI")
    val collectionURI: String,
    @SerialName("items")
    val items: List<ComicSummaryDto>,
)

@Serializable
data class ComicSummaryDto(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String,
)
