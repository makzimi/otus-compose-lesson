package ru.otus.compose.data.dto.heroinfo

import ru.otus.compose.data.dto.ThumbNailDto
import ru.otus.compose.data.dto.getImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.compose.ui.common.DataViewState

@Serializable
data class HeroInfoDto(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto,
    @SerialName("comics")
    val comicsDto: ComicsDto
)

fun HeroInfoDto.toDataViewModel(navigationUrl: String): DataViewState {
    return DataViewState(
        title = name,
        imageUrl = thumbnail.getImageUrl(),
        navigationLink = navigationUrl
    )
}