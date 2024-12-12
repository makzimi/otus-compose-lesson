package ru.otus.compose.features.heroes

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.otus.compose.data.dto.HeroDto
import ru.otus.compose.data.repository.HeroRepository
import ru.otus.compose.data.source.HeroDataSource
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel
@Inject constructor(
    private var heroRepository: HeroRepository
) : ViewModel(), LifecycleObserver {

    val heroes: Flow<PagingData<HeroDto>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        HeroDataSource(heroRepository)
    }.flow.cachedIn(viewModelScope)
}