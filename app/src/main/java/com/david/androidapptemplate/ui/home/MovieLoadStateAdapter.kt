package com.david.androidapptemplate.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.david.androidapptemplate.R
import com.david.androidapptemplate.getApp
import com.david.androidapptemplate.inflater
import com.david.androidapptemplate.ui.base.BaseHolder
import kotlinx.android.synthetic.main.load_state_view.*

/**
 * Created by David Harush
 * On 29/10/2020.
 *
 * for more info https://proandroiddev.com/how-to-use-the-paging-3-library-in-android-5d128bb5b1d8
 *
 */
class MovieLoadStateAdapter
 : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    private val mInflater: LayoutInflater by lazy {  getApp().inflater }


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.onBind(loadState)


    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            mInflater
                .inflate(R.layout.load_state_view, parent, false)
        )
    }

    inner class LoadStateViewHolder(itemView: View) : BaseHolder<LoadState>(itemView){

        override fun onBind(dataItem: LoadState) {

            txtErrorMessage.isVisible = dataItem !is LoadState.Loading
            progress.isVisible = dataItem is LoadState.Loading

            if (loadState is LoadState.Error){
                txtErrorMessage.text = (loadState as LoadState.Error).error.localizedMessage
            }

        }

    }
}