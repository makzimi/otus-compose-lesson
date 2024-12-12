package ru.otus.compose.data.dto

import ru.otus.compose.data.dto.heroinfo.HeroResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarvelPagingDto(
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("results")
    val results: List<HeroResponse>
)