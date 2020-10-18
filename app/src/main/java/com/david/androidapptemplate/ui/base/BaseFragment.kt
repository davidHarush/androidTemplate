package com.david.androidapptemplate.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.david.androidapptemplate.BuildConfig
import com.david.haru.lifecyclelog.LifecycleLog

abstract class BaseFragment : Fragment {

    constructor() : super()

    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifecycleLog(lifecycle = lifecycle, isDebug = BuildConfig.DEBUG)
    }

    abstract fun getFragmentName(): String

}