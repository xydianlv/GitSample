package com.example.gitsample.system.notify

enum class NotifyTestType(val title: String, val info: String) {

    NOTIFY_BUILDER(
        "NotifyBuilder",
        "通过 NotifyBuilder 创建通知，无震动无声音"
    ),

    NOTIFY_BUILDER_V(
        "NotifyBuilderV",
        "通过 NotifyBuilder 创建通知，有震动无声音"
    ),

    NOTIFY_BUILDER_S(
        "NotifyBuilderS",
        "通过 NotifyBuilder 创建通知，无震动有声音"
    ),

    NOTIFY_BUILDER_VS(
        "NotifyBuilderVS",
        "通过 NotifyBuilder 创建通知，有震动有声音"
    ),

    NOTIFY_CHANNEL_V(
        "NotifyChannelV",
        "通过 NotifyChannel 创建通知，有震动无声音"
    ),

    NOTIFY_CHANNEL_S(
        "NotifyChannelS",
        "通过 NotifyChannel 创建通知，无震动有声音"
    ),

    NOTIFY_CHANNEL_VS(
        "NotifyChannelVS",
        "通过 NotifyChannel 创建通知，有震动有声音"
    )
}