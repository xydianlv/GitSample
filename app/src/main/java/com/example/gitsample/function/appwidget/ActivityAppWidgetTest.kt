package com.example.gitsample.function.appwidget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityAppWidgetTestBinding
import com.example.gitsample.function.appwidget.provider.KunWidgetType
import com.example.gitsample.utils.TimeUtils
import com.example.gitsample.utils.ZToast

class ActivityAppWidgetTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityAppWidgetTest::class.java))
        }

        private const val MAX_LOG_LENGTH = 52
    }

    private lateinit var binding: ActivityAppWidgetTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppWidgetTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        KunWidgetUtils.registerLogListener(null)
    }

    private fun initActivity() {
        initToolbar()
        initClick()
        onRegisterLogListener()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.APP_WIDGET.title)
    }

    private fun initClick() {
        val clickListener = View.OnClickListener {
            when (it.id) {
                R.id.preview_small -> {
                    KunWidgetUtils.addKunWidget(
                        this@ActivityAppWidgetTest,
                        KunWidgetType.KUN_WIDGET_SMALL
                    ) { result ->
                        if (result) {
                            ZToast.show("添加成功")
                        } else {
                            ZToast.show("添加失败")
                        }
                    }
                }
                R.id.preview_big -> {
                    KunWidgetUtils.addKunWidget(
                        this@ActivityAppWidgetTest,
                        KunWidgetType.KUN_WIDGET_BIG
                    ) { result ->
                        if (result) {
                            ZToast.show("添加成功")
                        } else {
                            ZToast.show("添加失败")
                        }
                    }
                }
                R.id.refresh -> {
                    KunWidgetUtils.tryRefreshKunWidget(this@ActivityAppWidgetTest, false)
                }
            }
        }
        binding.previewSmall.setOnClickListener(clickListener)
        binding.previewBig.setOnClickListener(clickListener)
        binding.refresh.setOnClickListener(clickListener)
    }

    private fun onRegisterLogListener() {
        KunWidgetUtils.registerLogListener(object : OnWidgetLogListener {
            override fun onLog(logInfo: String) {
                val nextLog = if (logInfo.length > MAX_LOG_LENGTH) {
                    logInfo.subSequence(0, MAX_LOG_LENGTH)
                } else {
                    logInfo
                }
                binding.logInfo.text = StringBuilder(TimeUtils.getCurrentFormatTime())
                    .append(" ")
                    .append(nextLog)
                    .append("\n")
                    .append(binding.logInfo.text.toString())
                    .toString()
            }
        })
    }
}