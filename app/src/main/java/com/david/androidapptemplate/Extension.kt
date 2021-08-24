package com.david.androidapptemplate

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.david.haru.myextensions.baseContext

/**
 * Created by David Harush
 * On 21/10/2020.
 */

fun ImageView.loadImage(
    @NonNull url: String,
    @DrawableRes error: Int = 0,
    @DrawableRes holder: Int = 0
) {
    Glide
        .with(baseContext)
        .load(url)
        .error(error)
        .placeholder(holder)
        .into(this)
}


val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)
