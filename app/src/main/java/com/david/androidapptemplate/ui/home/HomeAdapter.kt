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
import com.david.androidapptemplate.ui.base.BaseHolder
import com.david.androidapptemplate.ui.main.MainViewModel
import kotlinx.android.synthetic.main.news_item.*
import com.david.androidapptemplate.model.News.Item as DataItem


class HomeAdapter(
    private val callBack: CallBack,
    private val mainViewModel: MainViewModel
) : ListAdapter<DataItem, HomeAdapter.NewsViewHolder>(
    NewsDiffCallback()
) {
    private var mInflater: LayoutInflater = LayoutInflater.from(getApp() as App)

    fun setData(data: List<DataItem>): HomeAdapter {
        submitList(data)
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(mInflater.inflate(R.layout.news_item, parent, false))
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    inner class NewsViewHolder(itemView: View) : BaseHolder<DataItem>(itemView) {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onBind(dataItem: DataItem) {
            itemView.tag = dataItem
            title.text = dataItem.title.trim()
            index.text = "${adapterPosition + 1}. "
        }

        override fun onClick(v: View?) {
//            callBack.onItemClick(itemView.tag as DataItem)

            val data = itemView.tag as DataItem
            mainViewModel.setSelectedItem(data)

            val transitionName = "transitionName${data}"
            title.transitionName = transitionName
            val extras = FragmentNavigatorExtras(title to transitionName)

            callBack.getNavController().navigate(
                R.id.DetailsFragment,
                null, // Bundle of args
                null, // NavOptions
                extras)

        }

    }

    class NewsDiffCallback : DiffUtil.ItemCallback<DataItem>() {

        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }

    interface CallBack {
        fun onItemClick(item: DataItem)
        fun getNavController() : NavController
    }

}