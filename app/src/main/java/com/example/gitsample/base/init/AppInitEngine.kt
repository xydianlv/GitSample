package com.example.gitsample.base.init

import com.example.gitsample.base.init.base.IInitDelegate
import com.example.gitsample.base.init.utils.AppLaunchThreadPool
import com.example.gitsample.base.init.utils.AppStartLog

enum class AppInitEngine {

    INSTANCE;

    private val groupList: ArrayList<List<IInitDelegate>> = ArrayList()
    private val mainList: ArrayList<IInitDelegate> = ArrayList()

    private val delegateMap: HashMap<Int, IInitDelegate> = HashMap()
    private var onClear: Boolean = false

    // 分组并行处理，每一组模块儿并行初始化结束后，进入下一组模块儿初始化
    // 往 group 中添加 delegate 顺序遵循：无依赖模块儿的尽量靠前
    fun group(vararg delegateArray: IInitDelegate): AppInitEngine {
        val delegateList = ArrayList<IInitDelegate>()
        for (delegate in delegateArray) {
            if (delegateMap.containsKey(delegate.getModule())) {
                continue
            }
            if (delegate.onMainThread()) {
                mainList.add(delegate)
            } else {
                delegateList.add(delegate)
            }
            delegateMap[delegate.getModule()] = delegate
        }
        groupList.add(delegateList)
        return this
    }

    fun init() {
        AppStartLog.onLog(0, "AppInitEngine.init() -> delegateSize : " + delegateMap.size)
        checkLinkMap()
        initGroup(0)
        initMainList()
    }

    private fun checkLinkMap() {
        delegateMap.forEach { entity ->
            val linkArray = entity.value.getLinkModuleArray()
            linkArray.forEach { module ->
                if (linkMap[module] == null) {
                    linkMap[module] = ArrayList()
                }
                linkMap[module]!!.add(entity.key)
            }
        }
    }

    private fun initGroup(index: Int) {
        if (index >= groupList.size) {
            return
        }
        val delegateList = groupList[index]
        if (delegateList.isEmpty()) {
            initGroup(index + 1)
        } else {
            initGroupModule(delegateList) {
                initGroup(index + 1)
            }
        }
    }

    private fun initGroupModule(list: List<IInitDelegate>, onFinishCallback: () -> Unit) {
        var onInitCount = 0
        for (delegate in list) {
            if (delegate.onInitFinish()) {
                onInitCount++
                continue
            }
            checkInit(delegate.getModule(), object : AppModuleInitCallback {
                override fun onFinish() {
                    if (checkAllInit(list)) {
                        onFinishCallback.invoke()
                    }
                }
            })
            delegate.doInit()
        }
        if (onInitCount == list.size) {
            onFinishCallback.invoke()
        }
    }

    private fun checkAllInit(list: List<IInitDelegate>): Boolean {
        list.forEach {
            if (!it.onInitFinish()) {
                return false
            }
        }
        return true
    }

    private fun initMainList() {
        if (mainList.isEmpty()) {
            return
        }
        for (delegate in mainList) {
            if (delegate.onInitFinish()) {
                continue
            }
            delegate.doInit()
        }
    }

    private val callbackMap: HashMap<Int, ArrayList<AppModuleInitCallback>> = HashMap()
    private val linkMap: HashMap<Int, ArrayList<Int>> = HashMap()

    fun getDelegate(@AppInitModule module: Int): IInitDelegate? {
        return delegateMap[module]
    }

    fun onInitFinish(@AppInitModule module: Int) {
        callbackMap[module]?.forEach {
            it.onFinish()
        }
        linkMap[module]?.forEach {
            delegateMap[it]?.onLinkModuleInit(module)
        }
        if (onClear) {
            checkClear()
        }
    }

    fun checkInit(@AppInitModule module: Int, callback: AppModuleInitCallback) {
        if (delegateMap[module]?.onInitFinish() == true) {
            callback.onFinish()
        } else {
            if (callbackMap[module] == null) {
                callbackMap[module] = ArrayList()
            }
            callbackMap[module]!!.add(callback)
        }
    }

    fun checkClear() {
        onClear = true
        var initCount = 0
        delegateMap.forEach { entity ->
            if (entity.value.onInitFinish()) {
                initCount++
            }
        }
        if (initCount == delegateMap.size) {
            AppLaunchThreadPool.shutDown()
        }
    }
}