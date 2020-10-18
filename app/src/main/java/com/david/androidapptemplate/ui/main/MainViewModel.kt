package com.david.androidapptemplate.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.david.androidapptemplate.model.News
import com.david.androidapptemplate.model.ResultType
import com.david.androidapptemplate.repos.NewsRepo
import com.david.androidapptemplate.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val newsRepo: NewsRepo
) : BaseViewModel() {

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
        launch {
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