package com.david.androidapptemplate.model

/**
 * Created by David Harush
 * On 29/10/2020.
 */

import android.net.Uri

fun Movie.Item.isEmpty() = (this.id == 0 && this.overview.isEmpty() && this.title.isEmpty())

fun Movie.Item.getTransitionName() =  "transitionName_${id}"

fun Movie.Item.getImageUrl() = Uri.parse("https://image.tmdb.org/t/p/w500${poster_path}")!!

