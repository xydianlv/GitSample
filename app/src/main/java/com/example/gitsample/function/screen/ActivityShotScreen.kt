package com.example.gitsample.function.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.databinding.ActivityShotScreenBinding

class ActivityShotScreen : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityShotScreen::class.java))
        }
    }

    private lateinit var binding: ActivityShotScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShotScreenBinding.inflate(layoutInflater)

        initActivity()
    }

    private fun initActivity() {

    }
}