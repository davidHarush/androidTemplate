package com.david.androidapptemplate.network
import com.david.androidapptemplate.model.Movie
import retrofit2.http.GET

/**
 * Created by David Harush
 * On 29/10/2020.
 */


/**
 * Created by david-h.
 */
interface IMoviesApiService {

    companion object {
      public const val BASE_URL: String = "https://api.themoviedb.org/"
    }

    //    https://api.themoviedb.org/3/movie/popular?api_key=56a778f90174e0061b6e7c69a5e3c9f2&language=en-US
//    @GET("/3/movie/popular?api_key=56a778f90174e0061b6e7c69a5e3c9f2")
//    fun getPopularMovies(@Query("page") pageNumber: Int): Movie.Result

    //    https://api.themoviedb.org/3/movie/popular?api_key=56a778f90174e0061b6e7c69a5e3c9f2&language=en-US
    @GET("/3/movie/popular?api_key=56a778f90174e0061b6e7c69a5e3c9f2")
    suspend fun getPopularMovies(): Movie.ApiResult
}