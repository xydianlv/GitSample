package com.example.init

import androidx.annotation.IntDef

@IntDef(
    AppInitModule.ALL,
    AppInitModule.MAIN,
    AppInitModule.UI,
    AppInitModule.ANALYTIC
)
annotation class AppInitModule {

    companion object {

        const val ALL = 0

        const val MAIN = 1

        const val UI = 2

        const val ANALYTIC = 3
    }
}