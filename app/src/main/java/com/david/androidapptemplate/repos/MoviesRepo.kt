package com.david.androidapptemplate.repos

import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.network.IMoviesApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

//************
// **NOTE
// not in use in this Branch
//*************
@Singleton
class MoviesRepo @Inject constructor(
    private val webService: IMoviesApiService
) {

    suspend fun getMovies(): RepoResponse<Movie.ApiResult> {


        return try {
            val result: Movie.ApiResult = webService.getPopularMovies()
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