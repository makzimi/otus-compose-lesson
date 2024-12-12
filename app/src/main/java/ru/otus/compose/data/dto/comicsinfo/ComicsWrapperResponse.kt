package ru.otus.compose.data.dto.comicsinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicsWrapperResponse(
    @SerialName("data")
    val info: ComicsWrapperDto
)