package com.david.androidapptemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.david.androidapptemplate.R
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.ui.base.BaseFragment
import com.david.androidapptemplate.ui.home.HomeAdapter.Companion.MOVIE_VIEW_TYPE
import com.david.androidapptemplate.ui.main.MainViewModel
import com.david.haru.myextensions.gone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(), HomeAdapter.CallBack {

    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel>()
    private var viewHome: View? = null
    private var adapter : HomeAdapter? = null


    companion object {
        fun newInstance() =
            HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (viewHome == null) {
            //prevent redraw the fragment
            viewHome = inflater.inflate(R.layout.main_fragment, container, false)


        }

        return viewHome
    }

    /**cant use because sharedElementTransition, we need to prevent redraw the fragment*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (adapter == null) {
            adapter = HomeAdapter(
                callBack = this@HomeFragment,
                mainViewModel = mainViewModel
            )


            recyclerView.apply {
                this.adapter = (this@HomeFragment.adapter)?.withLoadStateFooter(footer = MovieLoadStateAdapter())

                val gridLayoutManager = GridLayoutManager(context, ScreenSizeUtil.getNumberOfColumnsByScreenSize())
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val viewType = this@HomeFragment.adapter?.getItemViewType(position)
                        return if(viewType == MOVIE_VIEW_TYPE) 1
                        else 2
                    }
                }

                setHasFixedSize(true)
                layoutManager =gridLayoutManager

            }
            setObserves()
        }

    }

    override fun getFragmentName(): String = HomeFragment::class.java.simpleName


    private fun setObserves() {
        lifecycle.coroutineScope.launch {
            mainViewModel.getData()?.collectLatest {
                adapter?.submitData(it)
            }
            mainViewModel.getData()?.collectLatest {
                adapter?.submitData(it)
            }

        }
        adapter!!.addLoadStateListener { loadState ->

            /**
            https://proandroiddev.com/how-to-use-the-paging-3-library-in-android-5d128bb5b1d8
            This code is taken from https://medium.com/@yash786agg/jetpack-paging-3-0-android-bae37a56b92d
             **/
            if (loadState.refresh is LoadState.Loading){
                // Loading
            }else{
                // end Loading

                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

            }

        }

        mainViewModel.onErr
            .observe(requireActivity(),
                Observer {
                    recyclerView.gone()
                })
    }

    override fun onItemClick(item: Movie.Item) {

    }

    override fun getNavController() = findNavController()

}
