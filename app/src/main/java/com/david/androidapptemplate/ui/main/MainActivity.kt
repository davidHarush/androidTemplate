package com.david.androidapptemplate.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.david.androidapptemplate.R
import com.david.androidapptemplate.hide
import com.david.androidapptemplate.show
import com.david.androidapptemplate.showBadge
import com.david.androidapptemplate.ui.base.BaseActivity
import com.david.haru.myextensions.gone
import com.david.haru.myextensions.pxToDp
import com.david.haru.myextensions.showToast
import com.david.haru.myextensions.visible
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener {

    private var isFABExpened = false


    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun getActivityName(): String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visible()
        err.gone()

    }

    override fun onStart() {
        super.onStart()
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(this)
        setObserves()
        setListeners()
        setBottomNavigation()

//        bottom_navigation.showBadge(menuItemId = R.id.SecondsEmptyFragment , amount = 5)

    }

    private fun setObserves() {
        viewModel.getData().observe(this, Observer {
            if (it.total_results >0) {
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
            bottom_navigation.hide()
        }
        if (destination.id == R.id.HomeFragment) {
            fab.hide()
            fab_up.hide()
            fab_down.hide()
            bottom_navigation.show()
        }
    }


    private fun setBottomNavigation() {

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.HomeFragment,
//                R.id.EmptyFragment,
//                R.id.SecondsEmptyFragment
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration) // no ActionBar !!!
        navView.setupWithNavController(navController)
        //   supportActionBar?.setDisplayShowTitleEnabled(false)

        if(navController.currentDestination?.id ==  R.id.DetailsFragment){
            bottom_navigation.hide()
        }

    }


}