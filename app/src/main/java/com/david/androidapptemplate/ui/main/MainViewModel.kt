package com.david.androidapptemplate.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.repos.MoviesRepo
import com.david.androidapptemplate.runCoroutine

class MainViewModel @ViewModelInject constructor(
    private val newsRepo: MoviesRepo
) : ViewModel() {

    private var newsFlash: MutableLiveData<Movie.ApiResult> = MutableLiveData()
    private var onErr: MutableLiveData<String> = MutableLiveData()
    private var selectedItem :  Movie.Item? = null


    // onErr
    fun getOnErr(): LiveData<String> {
        return onErr
    }

    // Get Data
    fun getData(): LiveData<Movie.ApiResult> {
        if (newsFlash.value == null) {
            fetchData()
        }
        return newsFlash
    }

    private fun fetchData() {
        runCoroutine {
           val request =  newsRepo.getMovies()
                if (request.isSuccessful) {
                    newsFlash.postValue(request.body)
                } else {
                    onErr.postValue(request.exception?.message)
                }

        }
    }

    fun setSelectedItem(data: Movie.Item) {
        selectedItem = data
    }
    fun getSelectedItem() =  selectedItem


}