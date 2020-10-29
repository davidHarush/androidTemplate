package com.david.androidapptemplate.model

/**
 * Created by David Harush
 * On 29/10/2020.
 */
object Movie {

    data class ApiResult(
        val page: Int,
        val results: ArrayList<Item>,
        val total_pages: Int,
        val total_results: Int
    )

   data class Item(
        val id: Int = 0,
        val overview: String = "",
        val popularity: Float = 0f,
        val poster_path: String = "",
        val release_date: String = "",
        val title: String = "",
        val video: Boolean = false,
        val vote_average: Float = 0f,
        val vote_count: Int = 0
    )
}