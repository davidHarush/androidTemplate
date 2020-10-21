package com.david.androidapptemplate.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.lifecycle.Observer
import com.david.androidapptemplate.R
import com.david.androidapptemplate.ui.base.BaseActivity
import com.david.haru.myextensions.gone
import com.david.haru.myextensions.pxToDp
import com.david.haru.myextensions.showToast
import com.david.haru.myextensions.visible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    var isFABExpened = false


    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun getActivityName(): String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        progressBar.visible()
        err.gone()

        viewModel.getNewsFlash().observe(this, Observer {
            if (it.item.isNotEmpty()) {
                progressBar.gone()
                err.gone()
            }
        })

        viewModel.getOnErr().observe(this, Observer {
            err.visible()
            err.text = it
            progressBar.gone()
        })

        setListeners()

    }

    private fun setListeners() {
        fab.setOnClickListener {
            if (isFABExpened) {
                isFABExpened = false;
                fab_down.animate().translationY(0.pxToDp.toFloat())
                fab_up.animate().translationY(0.pxToDp.toFloat())
            } else {
                isFABExpened = true;
                fab_down.animate().translationY(-400.pxToDp.toFloat())
                fab_up.animate().translationY(-800.pxToDp.toFloat())
            }
        }

        fab_up.setOnClickListener { showToast("thumb up") }
        fab_down.setOnClickListener { showToast("thumb down") }


    }


}