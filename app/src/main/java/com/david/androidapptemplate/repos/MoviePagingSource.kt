package com.david.androidapptemplate.repos

import androidx.paging.PagingSource
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.network.IMoviesApiService
import com.david.haru.myextensions.showToast
import kotlinx.coroutines.delay

/**
 * Created by David Harush
 * On 29/10/2020.
 */
class MoviePagingSource(
    private val movieApiService: IMoviesApiService
) : PagingSource<Int, Movie.Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie.Item> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = movieApiService.getPopularMovies(nextPage)
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}