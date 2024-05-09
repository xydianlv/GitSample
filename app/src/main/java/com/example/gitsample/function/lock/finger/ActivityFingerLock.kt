package com.example.gitsample.function.lock.finger

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.databinding.ActivityFingerLockBinding
import com.example.widget.activity.BaseActivity

class ActivityFingerLock : BaseActivity<ActivityFingerLockBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFingerLock::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    override fun getViewBinding(): ActivityFingerLockBinding {
        return ActivityFingerLockBinding.inflate(layoutInflater)
    }

    private fun initActivity() {

    }
}