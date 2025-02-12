package com.example.gitsample.system.proto

import android.content.res.Resources
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.FileType
import com.example.gitsample.system.file.PathManager
import com.example.gitsample.system.proto.data.InfoValueOuterClass
import com.example.resource.R
import com.example.utils.ZLog
import com.example.widget.view.ZToast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ProtoTestVM : ViewModel() {

    val infoListLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val resultListLiveData: MutableLiveData<List<String>> = MutableLiveData()

    private val onSelectedValueMap: HashMap<Int, String> = HashMap()

    fun loadInfoList(resource: Resources) {
        val result: ArrayList<String> = ArrayList()
        result.add(resource.getString(R.string.text_short_cn))
        result.add(resource.getString(R.string.text_long_cn))
        result.add(resource.getString(R.string.text_short_en))
        result.add(resource.getString(R.string.text_long_en))
        result.add(resource.getString(R.string.text_long_cn))
        infoListLiveData.value = result
    }

    fun cacheOnSelectedValue(onIndex: Int, infoValue: String?) {
        if (TextUtils.isEmpty(infoValue)) {
            onSelectedValueMap.remove(onIndex)
        } else {
            onSelectedValueMap[onIndex] = infoValue!!
        }
    }

    fun isEmptyCacheValue(): Boolean {
        return onSelectedValueMap.isEmpty()
    }

    fun trySaveValueToFile() {
        try {
            val listBuilder = InfoValueOuterClass.InfoValueList.newBuilder()
            onSelectedValueMap.forEach { (key, value) ->
                val infoValue =
                    InfoValueOuterClass.InfoValue.newBuilder().setIndex(key).setInfoValue(value)
                        .build()
                listBuilder.addInfoList(infoValue)
            }
            val listByteValue = listBuilder.build().toByteArray()
            val fos = FileOutputStream(getCacheFilePath(), false)
            fos.write(listByteValue)
            fos.flush()
            ZToast.show("SaveSuccess")
        } catch (e: Exception) {
            ZToast.show("SaveError : " + e.message)
            ZLog.e(e)
        } finally {
            onSelectedValueMap.clear()
        }
    }

    private fun getCacheFilePath(): String {
        val dirPath = PathManager.manager().innerCachePath(FileType.TEMP)
        val fileName = "proto_temp"
        return File(dirPath, fileName).absolutePath
    }

    fun tryLoadInfoList() {
        try {
            val fis = FileInputStream(getCacheFilePath())
            val byteArray = fis.readBytes()
            fis.close()

            val listInfoValue = InfoValueOuterClass.InfoValueList.parseFrom(byteArray)
            val resultList: ArrayList<String> = ArrayList()
            listInfoValue?.infoListList?.forEach {
                resultList.add(it.infoValue)
            }
            resultListLiveData.value = resultList
            ZToast.show("LoadSuccess")
        } catch (e: Exception) {
            ZToast.show("LoadError : " + e.message)
            ZLog.e(e)
        }
    }
}