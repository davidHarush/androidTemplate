package com.david.androidapptemplate.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.androidapptemplate.model.News
import com.david.androidapptemplate.model.ResultType
import com.david.androidapptemplate.repos.NewsRepo
import com.david.androidapptemplate.runCoroutine

class MainViewModel @ViewModelInject constructor(
    private val newsRepo: NewsRepo
) : ViewModel() {

    private var newsFlash: MutableLiveData<News.NewsFlash> = MutableLiveData()
    private var onErr: MutableLiveData<String> = MutableLiveData()

    // onErr
    fun getOnErr(): LiveData<String> {
        return onErr
    }

    // Get Data - News Flash
    fun getNewsFlash(): LiveData<News.NewsFlash> {
        if (newsFlash.value == null) {
            fetchNewsFlash()
        }
        return newsFlash
    }

    private fun fetchNewsFlash() {
        runCoroutine {
            newsRepo.getNewsFlash().let {
                if (it.status == ResultType.SUCCESS) {
                    newsFlash.postValue(it.data)
                } else {
                    onErr.postValue(it.err.message)
                }
            }
        }
    }

}