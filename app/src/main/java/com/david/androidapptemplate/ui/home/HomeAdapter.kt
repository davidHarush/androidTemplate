package com.david.androidapptemplate.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.david.androidapptemplate.App
import com.david.androidapptemplate.R
import com.david.androidapptemplate.getApp
import com.david.androidapptemplate.loadImage
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.model.getImageUrl
import com.david.androidapptemplate.model.getTransitionName
import com.david.androidapptemplate.ui.base.BaseHolder
import com.david.androidapptemplate.ui.main.MainViewModel
import kotlinx.android.synthetic.main.list_item.*


class HomeAdapter(
    private val callBack: CallBack,
    private val mainViewModel: MainViewModel
) : ListAdapter<Movie.Item, HomeAdapter.NewsViewHolder>(
    NewsDiffCallback()
) {
    private var mInflater: LayoutInflater = LayoutInflater.from(getApp() as App)

    fun setData(data:ArrayList<Movie.Item>): HomeAdapter {
        submitList(data)
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(mInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(currentList[position])
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
//            callBack.onItemClick(itemView.tag as DataItem)

            val data = itemView.tag as Movie.Item
            mainViewModel.setSelectedItem(data)

            val transitionName = data.getTransitionName()
            image.transitionName = transitionName+"image"
            title.transitionName = transitionName+"title"
            val extras = FragmentNavigatorExtras(image to image.transitionName, title to title.transitionName)

            callBack.getNavController().navigate(
                R.id.DetailsFragment,
                null, // Bundle of args
                null, // NavOptions
                extras)

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

    interface CallBack {
        fun onItemClick(item: Movie.Item)
        fun getNavController() : NavController
    }

}