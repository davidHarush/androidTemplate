package com.david.androidapptemplate.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.david.androidapptemplate.*
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.model.getImageUrl
import com.david.androidapptemplate.model.getTransitionName
import com.david.androidapptemplate.ui.base.BaseHolder
import com.david.androidapptemplate.ui.main.MainViewModel
import com.david.haru.myextensions.baseContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.list_item.*

/**
 * for more info https://proandroiddev.com/how-to-use-the-paging-3-library-in-android-5d128bb5b1d8
 */
class HomeAdapter(
    private val mainViewModel: MainViewModel,
    private val nav : NavController
) : PagingDataAdapter<Movie.Item, HomeAdapter.NewsViewHolder>(
    NewsDiffCallback()
) {
    private val mInflater: LayoutInflater by lazy { baseContext.inflater }

    companion object {
        var FOOTER_VIEW_TYPE: Int = 1
        var MOVIE_VIEW_TYPE: Int = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            FOOTER_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(mInflater.inflate(R.layout.list_item, parent, false))
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val dataItem: Movie.Item = getItem(position)!!
        holder.onBind(dataItem)
    }

    inner class NewsViewHolder(itemView: View) : BaseHolder<Movie.Item>(itemView) {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onBind(dataItem: Movie.Item) {
            itemView.tag = dataItem
            title.text = dataItem.title.trim()
            image.loadImage(url = dataItem.getImageUrl().toString())
        }

        override fun onClick(v: View?) {
            val data = itemView.tag as Movie.Item
            mainViewModel.setSelectedItem(data)

            val transitionName = data.getTransitionName()
            image.transitionName = transitionName + "image"
            title.transitionName = transitionName + "title"
            val extras = FragmentNavigatorExtras(
                image to image.transitionName,
                title to title.transitionName
            )

            nav.navigate(
                R.id.DetailsFragment,
                null, // Bundle of args
                null, // NavOptions
                extras
            )
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<Movie.Item>() {

        override fun areItemsTheSame(oldItem: Movie.Item, newItem: Movie.Item): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Movie.Item, newItem: Movie.Item): Boolean {
            return oldItem == newItem
        }

    }

}