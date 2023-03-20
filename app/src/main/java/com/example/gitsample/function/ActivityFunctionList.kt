package com.example.gitsample.function

import android.content.Context
import android.content.Intent
import com.example.gitsample.base.BaseActivity

class ActivityFunctionList : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFunctionList::class.java))
        }
    }
}