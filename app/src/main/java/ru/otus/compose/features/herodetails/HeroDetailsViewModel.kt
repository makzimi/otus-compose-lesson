package ru.otus.compose.features.herodetails

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.compose.data.dto.GreatResult
import ru.otus.compose.data.dto.heroinfo.HeroInfoDto
import ru.otus.compose.data.repository.HeroRepository
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel
@Inject constructor(
    private var heroRepository: HeroRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchHeroInfo(heroId: Long): GreatResult<HeroInfoDto> {
        return try {
            heroRepository.loadHeroInfoById(heroId)
        } catch (exception: Exception) {
            GreatResult.Error(exception)
        }
    }
}
