package com.david.androidapptemplate.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.network.IMoviesApiService
import com.david.androidapptemplate.repos.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel @ViewModelInject constructor(
    private val webService: IMoviesApiService
) : ViewModel() {
    private var movies: Flow<PagingData<Movie.Item>>? =  null
    private var onErr: MutableLiveData<String> = MutableLiveData()
    private var selectedItem :  Movie.Item? = null


    // onErr
    fun getOnErr(): LiveData<String> {
        return onErr
    }
    fun setErr(err : String) {
        onErr.postValue(err)
    }

    // Get Data
    fun getData():  Flow<PagingData<Movie.Item>>? {
        if (movies == null) {
            fetchData()
        }
        return movies
    }

    private fun fetchData() {
            movies = Pager(PagingConfig(pageSize = 20)) {
                MoviePagingSource(webService)
            }.flow
                .cachedIn(viewModelScope)
    }

    fun setSelectedItem(data: Movie.Item) {
        selectedItem = data
    }
    fun getSelectedItem() =  selectedItem


}