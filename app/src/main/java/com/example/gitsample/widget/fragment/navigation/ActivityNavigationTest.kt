package com.example.gitsample.widget.fragment.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.example.base.PageType
import com.example.base.listener.OnComClickListener
import com.example.gitsample.R
import com.example.gitsample.databinding.ActivityNavigationTestBinding
import com.example.widget.activity.BaseActivity
import com.example.widget.fragment.text.FragTextParams

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
        initView()
        initNav()
        bindNav()
    }

    override fun getViewBinding(): ActivityNavigationTestBinding {
        return ActivityNavigationTestBinding.inflate(layoutInflater)
    }

    private fun initView() {
        binding.toolbar.initShow(PageType.FRAGMENT_NAVIGATION_TEST.title)
    }

    private fun initNav() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.frag_container)
        if (fragment is NavHostFragment) {
            fragment.also { navHostFragment = it }
        }
    }

    private fun bindNav() {
        navController()?.apply {
            val navGraph: NavGraph = navInflater.inflate(R.navigation.nav_navigation_test)
            navGraph.setStartDestination(R.id.frag_00)
            setGraph(navGraph, buildPage00Params().build())
        }
    }

    private fun navController(): NavController? {
        return navHostFragment?.navController
    }

    private fun buildPage00Params(): FragTextParams {
        val clickListener: OnComClickListener = object : OnComClickListener {
            override fun onClick(key: Int, vararg params: Any?) {
                navController()?.apply {
                    navigate(R.id.frag_01, buildPage01Params().build())
                }
            }
        }
        return FragTextParams.obj().textValue("Page-0").btnTextValue("Next ->", clickListener)
    }

    private fun buildPage01Params(): FragTextParams {
        val clickListener: OnComClickListener = object : OnComClickListener {
            override fun onClick(key: Int, vararg params: Any?) {
                navController()?.apply {
                    navigate(R.id.frag_02, buildPage02Params().build())
                }
            }
        }
        return FragTextParams.obj().textValue("Page-1").btnTextValue("Next ->", clickListener)
    }

    private fun buildPage02Params(): FragTextParams {
        val clickListener: OnComClickListener = object : OnComClickListener {
            override fun onClick(key: Int, vararg params: Any?) {
                finish()
            }
        }
        return FragTextParams.obj().textValue("Page-2").btnTextValue("Finish", clickListener)
    }
}