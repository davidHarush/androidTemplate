package com.david.androidapptemplate.ui.empty

import android.os.Bundle
import android.view.View
import com.david.androidapptemplate.R
import com.david.androidapptemplate.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.empty_fragment.*

@AndroidEntryPoint
class EmptyFragment : BaseFragment(R.layout.empty_fragment) {

    override fun getFragmentName(): String = EmptyFragment::class.java.simpleName

    companion object {
        fun newInstance() =
            EmptyFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = "Hello from ${getFragmentName()}"
    }

}
