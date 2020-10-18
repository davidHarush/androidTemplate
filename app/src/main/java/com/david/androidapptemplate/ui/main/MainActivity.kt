package com.david.androidapptemplate.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.david.androidapptemplate.R
import com.david.androidapptemplate.ui.base.BaseActivity
import com.david.haru.myextensions.gone
import com.david.haru.myextensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*



@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun getActivityName(): String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        progressBar.visible()
        err.gone()

        viewModel.getNewsFlash().observe(this, Observer {
            if(it.item.isNotEmpty()){
                progressBar.gone()
                err.gone()
            }
        })

        viewModel.getOnErr().observe(this, Observer {
            err.visible()
            err.text = it
            progressBar.gone()
        })
    }



}