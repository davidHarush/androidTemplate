package com.david.androidapptemplate.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.transition.TransitionInflater
import com.david.androidapptemplate.R
import com.david.androidapptemplate.ui.base.BaseFragment
import com.david.androidapptemplate.ui.main.MainViewModel
import com.david.haru.myextensions.fadeInAnimate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.details_fragment.*

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.details_fragment) {

    override fun getFragmentName(): String = DetailsFragment::class.java.simpleName
    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel>()

    companion object {
        fun newInstance() =
            DetailsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = mainViewModel.getSelectedItem()!!

        detailsTitle.transitionName = "transitionName${data}"
        detailsTitle.text = data.title
        detailsSubTitle.text = data.subTitle
        detailsSubTitle.fadeInAnimate()

    }


}
