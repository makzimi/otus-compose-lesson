package ru.otus.compose.features.comicdetails

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.compose.common.resolve
import ru.otus.compose.data.model.Comic
import ru.otus.compose.data.repository.ComicsRepository
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchComicDetails(comicId: String): ComicDetailsState {
        val result = comicsRepository.loadComicDetails(comicId)

        return result.resolve(
            onSuccess = { comic -> comic.toDetailsState() },
            onError = { throwable -> ComicDetailsState.Error(throwable) }
        )
    }

    private fun Comic.toDetailsState(): ComicDetailsState.Data {
        return ComicDetailsState.Data(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl,
        )
    }
}
