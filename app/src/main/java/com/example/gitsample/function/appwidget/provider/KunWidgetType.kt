package com.example.gitsample.function.appwidget.provider

import androidx.annotation.IntDef

@IntDef(
    KunWidgetType.KUN_WIDGET_SMALL,
    KunWidgetType.KUN_WIDGET_BIG
)
annotation class KunWidgetType {

    companion object {

        const val KUN_WIDGET_SMALL = 1

        const val KUN_WIDGET_BIG = 2
    }
}