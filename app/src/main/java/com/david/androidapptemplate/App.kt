package com.david.androidapptemplate

import android.os.StrictMode
import com.david.haru.myextensions.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApplication() {


    override fun onCreate() {
        super.onCreate()
//        setupStrictMode()
        setTimber()
        setGlide()
        /*
         * ..
         * ..
         * ..
         *
         */
    }

    private fun setGlide() {


    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

    private fun setTimber() {
//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree()) ViewRootImpl
//        }
    }

}