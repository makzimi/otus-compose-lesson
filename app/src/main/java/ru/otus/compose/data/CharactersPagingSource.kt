package ru.otus.compose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.otus.compose.common.resolve
import ru.otus.compose.data.repository.CharactersRepository
import ru.otus.compose.data.model.Character

class CharactersPagingSource(
    private val repository: CharactersRepository,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val offset = params.key ?: 0
        val result = repository.loadCharacters(offset, 50)

        return result.resolve(
            onSuccess = { value ->
                val filtered = value.characters.filter { it.comicNames.isNotEmpty() }
                LoadResult.Page(
                    data = filtered,
                    prevKey = if (offset == 0) null else offset - 50,
                    nextKey = value.offset.plus(50)
                )
            },
            onError = { throwable ->
                LoadResult.Error(throwable)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int {
        return 0
    }
}
