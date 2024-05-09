package com.example.init.base

import com.example.init.AppInitModule

interface IInitDelegate {

    // 初始化该模块儿
    fun doInit()

    // 是否需要在主线程初始化
    fun onMainThread(): Boolean

    // 判断该模块儿是否初始化完成
    fun onInitFinish(): Boolean

    // 是否因为模块儿初始化出错，而中断初始化
    fun needCrashInit():Boolean

    // 是否需要在子线程中，手动 prepareLooper
    fun needPrepareLooper(): Boolean

    // 是否只能在主进程初始化
    fun onlyOnMainProcess(): Boolean

    // 所依赖的模块儿初始化完成后，通知当前模块儿
    fun onLinkModuleInit(@AppInitModule module: Int)

    // 当前模块儿的 Key
    @AppInitModule
    fun getModule(): Int

    // 所依赖模块儿的 KeyArray
    @AppInitModule
    fun getLinkModuleArray(): IntArray
}