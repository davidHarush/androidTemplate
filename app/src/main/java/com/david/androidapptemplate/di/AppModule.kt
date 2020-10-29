package com.david.androidapptemplate.di

import com.david.androidapptemplate.network.IMoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    companion object {
        private const val TIME_OUT = 5L
    }


    @Provides
    @Singleton
    fun provideGlobalOkHttpClient(
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideWebService(
       okHttpClient: OkHttpClient
    ): IMoviesApiService = createWebService(okHttpClient,
        IMoviesApiService.BASE_URL
    )


    private inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String,
        converterFactory: Converter.Factory = GsonConverterFactory.create()
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
        return retrofit.create(T::class.java)
    }

}