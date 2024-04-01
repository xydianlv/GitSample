package com.example.gitsample.widget.text.span

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object TextSpanUtils {

    private const val TEXT_VALUE = "这是一段002测试Span的文案"
    private const val INDEX_NEXT = 11
    private const val INDEX_PRE = 3

    @JvmStatic
    fun loadSpanTestList(callback: (List<SpanInfoData>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.IO) {
                val list = ArrayList<SpanInfoData>()
                list.add(loadResizeSpan())
                list
            }
            callback(list)
        }
    }

    @JvmStatic
    fun loadResizeSpan(): SpanInfoData {
        val spanString = SpannableStringBuilder(TEXT_VALUE)
        val resizeSpan = RelativeSizeSpan(1.4f)
        spanString.setSpan(resizeSpan, INDEX_PRE, INDEX_NEXT, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        return SpanInfoData("RelativeSizeSpan", spanString)
    }
}