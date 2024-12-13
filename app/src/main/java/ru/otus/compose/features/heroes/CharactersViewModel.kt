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
import ru.otus.compose.data.repository.CharactersRepository
import ru.otus.compose.data.CharactersPagingSource
import ru.otus.compose.data.model.Character
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel
@Inject constructor(
    private var charactersRepository: CharactersRepository
) : ViewModel(), LifecycleObserver {

    val characters: Flow<PagingData<Character>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        CharactersPagingSource(charactersRepository)
    }.flow.cachedIn(viewModelScope)
}
