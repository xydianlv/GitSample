package com.example.gitsample.function.shortcut

import android.os.Bundle
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityShortcutTargetBinding

class ActivityShortcutTarget : BaseActivity() {

    private lateinit var binding: ActivityShortcutTargetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutTargetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        binding.toolbar.initShow(PageType.SHORTCUT_TARGET.title)
    }
}