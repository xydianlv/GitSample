package com.example.gitsample.function.shortcut

import android.os.Bundle
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityShortcutTargetBinding
import com.example.widget.activity.BaseActivity

class ActivityShortcutTarget : BaseActivity<ActivityShortcutTargetBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivity()
    }

    override fun getViewBinding(): ActivityShortcutTargetBinding {
        return ActivityShortcutTargetBinding.inflate(layoutInflater)
    }

    private fun initActivity() {
        binding.toolbar.initShow(PageType.SHORTCUT_TARGET.title)
    }
}