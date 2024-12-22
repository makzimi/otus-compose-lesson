package ru.otus.compose

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Characters

@Serializable
data class ComicsCollection(val comicsCollectionId: String)

@Serializable
data class Character(val character: Long)

@Serializable
data class ComicInfo(val comicInfoId: String)
