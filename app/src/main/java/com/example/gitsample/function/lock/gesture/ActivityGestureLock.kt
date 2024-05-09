package com.example.gitsample.function.lock.gesture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gitsample.databinding.ActivityGestureLockBinding
import com.example.widget.view.ZToast
import com.example.widget.activity.BaseActivity

class ActivityGestureLock : BaseActivity<ActivityGestureLockBinding>() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityGestureLock::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivity()
    }

    override fun getViewBinding(): ActivityGestureLockBinding {
        return ActivityGestureLockBinding.inflate(layoutInflater)
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