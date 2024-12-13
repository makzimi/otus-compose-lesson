package ru.otus.compose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.compose.ui.common.DataViewState

@Serializable
data class ComicDataWrapperDto(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("data")
    val data: ComicDataContainerDto
)

@Serializable
data class ComicDataContainerDto(
    @SerialName("results")
    val results: List<ComicDto>
)

@Serializable
data class ComicDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String?,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto
)

fun ComicDto.toDataViewState(navLink: String): DataViewState {
    return DataViewState(
        title = title,
        imageUrl = thumbnail.getImageUrl(),
        navigationLink = navLink
    )
}


