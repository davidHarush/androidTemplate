package com.david.androidapptemplate.repos

import com.david.androidapptemplate.di.AppModule
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.network.IMoviesApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepo @Inject constructor(
    private val webService: IMoviesApiService
) {

    suspend fun getMovies(): RepoResponse<Movie.ApiResult> {


        return try {
            val result: Movie.ApiResult =   webService.getPopularMovies()
            RepoResponse(
                data = result,
                status = ResultType.SUCCESS
            )
        } catch (io: IOException) {
            RepoResponse(
                status = ResultType.FAIL,
                throwable = io
            )
        } catch (http: HttpException) {
            RepoResponse(
                status = ResultType.FAIL,
                throwable = http
            )
        }

    }

}