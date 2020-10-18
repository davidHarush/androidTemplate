package com.david.androidapptemplate.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.david.androidapptemplate.BuildConfig
import com.david.haru.lifecyclelog.LifecycleLog


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifecycleLog(lifecycle = lifecycle, isDebug = BuildConfig.DEBUG)
    }

    abstract fun getActivityName(): String
}