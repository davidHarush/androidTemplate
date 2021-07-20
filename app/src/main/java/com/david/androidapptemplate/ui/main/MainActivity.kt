package com.david.androidapptemplate.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.david.androidapptemplate.R
import com.david.androidapptemplate.ui.base.BaseActivity
import com.david.haru.myextensions.pxToDp
import com.david.haru.myextensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener {

    private var isFABExpened = false


    override fun getActivityName(): String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(this)
        setListeners()
    }

    private fun setListeners() {
        fab.setOnClickListener {
            if (isFABExpened) { // HIDE
                isFABExpened = false
                fab_down
                    .animate()
                    .translationY(0.pxToDp.toFloat())
                fab_up
                    .animate()
                    .translationY(0.pxToDp.toFloat())
            } else { // SHOW
                isFABExpened = true
                fab_down
                    .animate()
                    .translationY(-400.pxToDp.toFloat())
                fab_up
                    .animate()
                    .translationY(-800.pxToDp.toFloat())
            }
        }

        fab_up.setOnClickListener { showToast("thumb up") }
        fab_down.setOnClickListener { showToast("thumb down") }


    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id == R.id.DetailsFragment) {
            fab.show()
            fab_up.show()
            fab_down.show()
        }
        if (destination.id == R.id.HomeFragment) {
            fab.hide()
            fab_up.hide()
            fab_down.hide()
        }


    }


}