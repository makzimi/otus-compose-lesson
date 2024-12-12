package ru.otus.compose.data.dto.heroinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.compose.data.dto.ThumbNailDto
import ru.otus.compose.data.dto.getImageUrl
import ru.otus.compose.ui.common.DataViewState

@Serializable
data class HeroesResponseDto(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("data")
    val pagingInfo: PagingDataDto
)

@Serializable
data class PagingDataDto(
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("results")
    val results: List<HeroDto>
)

@Serializable
data class HeroDto(
    @SerialName("name")
    val name: String,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto,
    @SerialName("id")
    val id: Long
)

fun HeroDto.toDataView(navigationLink: String): DataViewState {
    return DataViewState(
        title = name,
        imageUrl = thumbnail.getImageUrl(),
        navigationLink = navigationLink
    )
}
