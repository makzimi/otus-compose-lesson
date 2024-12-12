package ru.otus.compose.data.dto.heroinfo

import ru.otus.compose.data.dto.MarvelPagingDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroResponseDto(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("data")
    val pagingInfo: MarvelPagingDto
)