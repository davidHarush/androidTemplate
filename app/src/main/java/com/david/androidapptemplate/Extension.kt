package com.david.androidapptemplate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.haru.myextensions.getBaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by David Harush
 * On 21/10/2020.
 */

fun ViewModel.runCoroutine(  block: suspend CoroutineScope.() -> Unit){
    viewModelScope.launch {
        block()
    }
}

fun getApp() = getBaseApp() as App
