package com.example.gitsample.widget.fragment.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.example.gitsample.R
import com.example.gitsample.databinding.ActivityNavigationTestBinding
import com.example.widget.activity.BaseActivity

class ActivityNavigationTest : BaseActivity<ActivityNavigationTestBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityNavigationTest::class.java))
        }
    }

    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNav()
        bindNav()
    }

    override fun getViewBinding(): ActivityNavigationTestBinding {
        return ActivityNavigationTestBinding.inflate(layoutInflater)
    }

    private fun initNav() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.frag_container)
        if (fragment is NavHostFragment) {
            fragment.also { navHostFragment = it }
        }
    }

    private fun bindNav() {
        navHostFragment?.apply {
            val navGraph: NavGraph =
                navController.navInflater.inflate(R.navigation.nav_navigation_test)
            navGraph.setStartDestination(R.id.frag_00)
            navController.setGraph(navGraph, null)
        }
    }
}