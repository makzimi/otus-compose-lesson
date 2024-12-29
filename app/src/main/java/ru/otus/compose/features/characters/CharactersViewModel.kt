package ru.otus.compose.features.characters

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.otus.compose.data.repository.CharactersRepository
import ru.otus.compose.data.CharactersPagingSource
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel
@Inject constructor(
    private var charactersRepository: CharactersRepository,
    private val mapper: CharacterItemStateMapper,
) : ViewModel(), LifecycleObserver {

    val characters: Flow<PagingData<CharacterItemState>> = Pager(PagingConfig(pageSize = 20)) {
        CharactersPagingSource(charactersRepository)
    }
        .flow
        .map { pagingData ->
            pagingData.map(mapper::map)
        }
        .cachedIn(viewModelScope)
}
