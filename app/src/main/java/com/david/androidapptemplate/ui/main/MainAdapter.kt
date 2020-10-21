package com.david.androidapptemplate.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.david.androidapptemplate.App
import com.david.androidapptemplate.R
import com.david.androidapptemplate.ui.base.BaseHolder
import com.david.haru.myextensions.getAppContext
import kotlinx.android.synthetic.main.news_item.*
import com.david.androidapptemplate.model.News.Item as DataItem


class MainAdapter(
    private val callBack: CallBack
) : ListAdapter<DataItem, MainAdapter.NewsViewHolder>(NewsDiffCallback()) {
    private var mInflater: LayoutInflater = LayoutInflater.from(getAppContext() as App)

    fun setData(data: List<DataItem>): MainAdapter {
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
            title.text = dataItem.title
            index.text = "${adapterPosition + 1}. "
        }

        override fun onClick(v: View?) {
            callBack.onItemClick(itemView.tag as DataItem)
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
    }

}