package com.david.androidapptemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.david.androidapptemplate.R
import com.david.androidapptemplate.model.Movie
import com.david.androidapptemplate.ui.base.BaseFragment
import com.david.androidapptemplate.ui.main.MainViewModel
import com.david.haru.myextensions.gone
import com.david.haru.myextensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

@AndroidEntryPoint
class HomeFragment : BaseFragment(), HomeAdapter.CallBack {

    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel>()
    private var viewHome: View? = null


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
            setObserves(viewHome!!)
        }

        return viewHome

    }

/**cant use because sharedElementTransition, we need to prevent redraw the fragment*/
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setObserves()
//    }

    override fun getFragmentName(): String = HomeFragment::class.java.simpleName


    private fun setObserves(v: View) {
        mainViewModel.getData()
            .observe(requireActivity(),
                Observer {movies ->
                    v.recyclerView.apply {
                        adapter = HomeAdapter(
                            callBack = this@HomeFragment,
                            mainViewModel = mainViewModel
                        ).setData(movies.results as ArrayList<Movie.Item>)
                        layoutManager = GridLayoutManager(context,  ScreenSizeUtil.getNumberOfColumnsByScreenSize())
                        visible()
                    }

                })

        mainViewModel.getOnErr()
            .observe(requireActivity(),
                Observer {
                    recyclerView.gone()
                })
    }

    override fun onItemClick(item: Movie.Item) {

    }

    override fun getNavController() = findNavController()

}
