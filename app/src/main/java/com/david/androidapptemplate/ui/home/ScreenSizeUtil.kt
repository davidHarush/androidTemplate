package com.david.androidapptemplate.ui.home

import android.content.Context

/**
 * Created by David Harush
 * On 29/10/2020.
 */

object ScreenSizeUtil {

    public fun getNumberOfColumnsByScreenSize(context: Context): Int {
        val width = context.resources.configuration.screenWidthDp
        val height = context.resources.configuration.screenHeightDp
        return if (height > width) { //portrait
            2
        } else { //landscape
            4
        }
    }
}