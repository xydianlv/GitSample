package com.example.gitsample.base.init

import androidx.annotation.IntDef

@IntDef(
    AppInitModule.ALL,
    AppInitModule.MAIN,
    AppInitModule.UI
)
annotation class AppInitModule {

    companion object {

        const val ALL = 0

        const val MAIN = 1

        const val UI = 2
    }
}