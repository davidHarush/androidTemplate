package com.david.androidapptemplate.repos

import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.network.IMoviesApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepo @Inject constructor(
    private val webService: IMoviesApiService
) {

    suspend fun getMovies(): SimpleResponse<Movie.ApiResult> {
        return safeApiCall { webService.getPopularMovies() }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }


}