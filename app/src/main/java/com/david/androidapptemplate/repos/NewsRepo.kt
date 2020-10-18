package com.david.androidapptemplate.repos

import com.david.androidapptemplate.model.News
import com.david.androidapptemplate.model.Response
import com.david.androidapptemplate.model.ResultType
import com.david.androidapptemplate.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepo @Inject constructor(
    private val webService: ApiService
) {

    suspend fun getNewsFlash(): Response<News.NewsFlash> {

        return try {
            val result: News.NewsFlash = webService.getNewsFlash()
            Response(data = result, status = ResultType.SUCCESS)
        } catch (io: IOException) {
            Response(data = null, status = ResultType.FAIL, err = io)
        } catch (http: HttpException) {
            Response(data = null, status = ResultType.FAIL, err = http)
        }

    }

}