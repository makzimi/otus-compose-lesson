package ru.otus.compose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.otus.compose.data.repository.HeroRepository
import ru.otus.compose.data.dto.HeroDto

class HeroPagingSource(
    private val repository: HeroRepository
) : PagingSource<Int, HeroDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroDto> {
        return try {
            val offset = params.key ?: 0
            val movieListResponse = repository.loadHeroes(offset, 20)

            LoadResult.Page(
                data = movieListResponse.pagingInfo.results,
                prevKey = if (offset == 0) null else offset - 20,
                nextKey = movieListResponse.pagingInfo.offset.plus(20)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HeroDto>): Int {
        return 0
    }
}