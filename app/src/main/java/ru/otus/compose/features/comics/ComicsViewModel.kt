package ru.otus.compose.features.comics

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.compose.data.dto.GreatResult
import ru.otus.compose.data.dto.comicsinfo.ComicsDto
import ru.otus.compose.data.dto.comicsinfo.ComicsWrapperDto
import ru.otus.compose.data.repository.ComicsRepository
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel
@Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchComicDetailInfo(comicId: String): GreatResult<ComicsDto> {
        return try {
            comicsRepository.loadComicDetailInfo(comicId)
        } catch (exception: Exception) {
            GreatResult.Error(exception)
        }
    }

    suspend fun fetchComicsInfoById(id: String): GreatResult<ComicsWrapperDto> {
        return try {
            comicsRepository.loadComicsById(id)
        } catch (exception: Exception) {
            GreatResult.Error(exception)
        }
    }
}