package ru.otus.compose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbNailDto(
    @SerialName("extension")
    val extension: String,
    @SerialName("path")
    val path: String,
)

fun ThumbNailDto.getImageUrl(): String {
    return "${this.path}.${this.extension}"
}