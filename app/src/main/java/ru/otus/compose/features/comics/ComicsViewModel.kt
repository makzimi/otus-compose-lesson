package ru.otus.compose.features.comics

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.compose.data.model.Comic
import ru.otus.compose.data.repository.ComicsRepository
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchComicDetails(comicId: String): Result<Comic> {
        return comicsRepository.loadComicDetails(comicId)
    }

    suspend fun fetchComicsForCharacter(characterId: String): Result<List<Comic>> {
        return comicsRepository.loadComicsForCharacter(characterId)
    }
}
