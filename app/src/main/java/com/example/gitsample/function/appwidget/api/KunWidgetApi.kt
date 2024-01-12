package com.example.gitsample.function.appwidget.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KunWidgetApi {

    fun getKunWidgetJson(callback: (json: JsonKunWidget?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val json = withContext(Dispatchers.IO) {
                val list = ArrayList<KunBubble>()
                list.add(KunBubble(1, 20, 1000))
                list.add(KunBubble(2, 20, 1000))
                list.add(KunBubble(3, 20, 1000))
                list.add(KunBubble(1, 20, 1000))
                val kunUrl = ""
                JsonKunWidget(12, 2023, kunUrl, list)
            }
            callback.invoke(json)
        }
    }
}