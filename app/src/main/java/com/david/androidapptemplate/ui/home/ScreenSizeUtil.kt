package com.david.androidapptemplate.ui.home

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.david.androidapptemplate.getApp

/**
 * Created by David Harush
 * On 29/10/2020.
 */

object ScreenSizeUtil {

    public fun getNumberOfColumnsByScreenSize(): Int {
        val dm = DisplayMetrics()
        val wm = getApp().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels

        return if (height > width) { //portrait
            2
        } else { //landscape
            4
        }
    }
}