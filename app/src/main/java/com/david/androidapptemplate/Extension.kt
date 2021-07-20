package com.david.androidapptemplate

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.david.haru.myextensions.getBaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by David Harush
 * On 21/10/2020.
 */
fun getApp() = getBaseApp() as App

val Any.classTag: String
    get() = this::class.java.simpleName

fun ImageView.loadImage(
    @NonNull url: String,
    @DrawableRes error: Int = 0,
    @DrawableRes holder: Int = 0
) {
    Glide
        .with(getApp())
        .load(url)
        .error(error)
        .placeholder(holder)
        .into(this)
}


val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)
