package com.david.androidapptemplate.network

import com.david.androidapptemplate.model.News
import retrofit2.http.GET

interface ApiService {

    // https://pastebin.com/raw/98BRjxnX
    @GET("/raw/98BRjxnX")
    suspend fun getNewsFlash(): News.NewsFlash

}