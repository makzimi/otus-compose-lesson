package ru.otus.compose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.compose.ui.common.DataViewState

@Serializable
data class ComicsWrapperResponseDto(
    @SerialName("data")
    val info: ComicsWrapperDto
)

@Serializable
data class ComicsWrapperDto(
    @SerialName("results")
    val results: List<ComicsDto>
) {
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
}
