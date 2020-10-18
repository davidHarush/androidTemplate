package com.david.androidapptemplate.model


object News {
    data class NewsFlash(
        val item: ArrayList<Item>
    )

    data class Item(
        val title: String,
        val subTitle: String,
        val pubDate: String
    )
}
