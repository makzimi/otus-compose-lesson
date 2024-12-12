package ru.otus.compose.data.dto.comicsinfo

import ru.otus.compose.data.dto.ThumbNailDto
import ru.otus.compose.data.dto.getImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.compose.ui.common.DataViewState

@Serializable
data class ComicsDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String?,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto
)

fun ComicsDto.toDataViewState(navLink: String): DataViewState {
    return DataViewState(
        title = title,
        imageUrl = thumbnail.getImageUrl(),
        navigationLink = navLink
    )
}