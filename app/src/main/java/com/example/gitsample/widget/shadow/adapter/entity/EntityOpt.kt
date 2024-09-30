package com.example.gitsample.widget.shadow.adapter.entity

import com.example.base.listener.ZCallback
import com.example.gitsample.widget.shadow.ShadowOptType

class EntityOpt(
    val optType: ShadowOptType,
    val optData: EntityOptData,
    val callback: ZCallback.ZCallbackF
)