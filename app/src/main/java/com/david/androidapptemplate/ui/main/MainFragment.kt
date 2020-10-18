package com.david.androidapptemplate.ui.main

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.androidapptemplate.R
import com.david.androidapptemplate.model.News
import com.david.androidapptemplate.ui.base.BaseFragment
import com.david.haru.myextensions.gone
import com.david.haru.myextensions.showToast
import com.david.haru.myextensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.main_fragment), MainAdapter.CallBack {

    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel>()


    companion object {
        fun newInstance() =
            MainFragment()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            setObserves()
    }


    override fun getFragmentName(): String = MainFragment::class.java.simpleName


    private fun setObserves() {
        mainViewModel.getNewsFlash()
            .observe(requireActivity(),
                Observer { result ->
                    recyclerView.adapter = MainAdapter(callBack = this@MainFragment).setData(result.item)
                    recyclerView.layoutManager =
                        LinearLayoutManager(context)
                    recyclerView.visible()
                })

        mainViewModel.getOnErr()
            .observe(requireActivity(),
                Observer {
                    recyclerView.gone()
                })
    }

    override fun onItemClick(item: News.Item) {
        showToast(item.title)
    }

}
