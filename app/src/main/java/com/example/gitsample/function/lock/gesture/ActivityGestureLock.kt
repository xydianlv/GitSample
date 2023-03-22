package com.example.gitsample.function.lock.gesture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.databinding.ActivityGestureLockBinding
import com.example.gitsample.utils.ZToast

class ActivityGestureLock : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityGestureLock::class.java))
        }
    }

    private lateinit var binding: ActivityGestureLockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestureLockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    override fun isLightStatusBar(): Boolean {
        return false
    }

    private fun initActivity() {
        binding.alert.setOnClickListener {
            ZToast.show("0、1、2、5、8")
        }
    }
}