package ru.otus.compose.data.dto.heroinfo

import ru.otus.compose.data.dto.ThumbNailDto
import ru.otus.compose.data.dto.getImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.compose.ui.common.DataViewState

@Serializable
data class HeroResponse(
    @SerialName("name")
    val name: String,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto,
    @SerialName("id")
    val id: Long
)

fun HeroResponse.toDataView(navigationLink: String): DataViewState {
    return DataViewState(
        title = name,
        imageUrl = thumbnail.getImageUrl(),
        navigationLink = navigationLink
    )
}