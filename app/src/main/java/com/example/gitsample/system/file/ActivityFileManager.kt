package com.example.gitsample.system.file

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.content.ContextCompat
import com.example.base.FileType
import com.example.resource.R
import com.example.base.PageType
import com.example.gitsample.databinding.ActivityFileManagerBinding
import com.example.utils.MemoryUtils
import com.example.widget.activity.BaseActivity
import com.example.widget.list.CommonListItemData

class ActivityFileManager : BaseActivity<ActivityFileManagerBinding>() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFileManager::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initView()
        initList()
    }

    override fun getViewBinding(): ActivityFileManagerBinding {
        return ActivityFileManagerBinding.inflate(layoutInflater)
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.FILE_MANAGER.title)
    }

    private fun initView() {
        binding.alert.setOnClickListener {
            binding.alert.text = resources.getText(com.example.gitsample.R.string.path_manager_alert)
            binding.alert.setTextColor(ContextCompat.getColor(this, R.color.ct_3))
        }
    }

    private fun initList() {
        val clickListener = View.OnClickListener {
            val tag = it.tag
            if (tag !is String || tag.isEmpty()) {
                return@OnClickListener
            }
            binding.alert.text = tag
            binding.alert.setTextColor(ContextCompat.getColor(this, R.color.color_alert))
        }
        binding.list.addItem(buildItemData(FileShowType.INNER_DATA_PATH) {
            it.tag = PathManager.manager().innerDataPath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.INNER_CACHE_PATH) {
            it.tag = PathManager.manager().innerCachePath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.EXTRA_FILE_PATH) {
            it.tag = PathManager.manager().extraFilePath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.EXTRA_CACHE_PATH) {
            it.tag = PathManager.manager().extraCachePath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.MEDIA_PATH) {
            it.tag = PathManager.manager().saveMediaPath()
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.ROOT_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getRootDirectory().absolutePath)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.DATA_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getDataDirectory().absolutePath)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.DOWNLOAD_CACHE_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getDownloadCacheDirectory().absolutePath)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.STORAGE_DIRECTORY) {
            val filePath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Environment.getStorageDirectory().absolutePath
            } else {
                Environment.getExternalStorageDirectory().absolutePath
            }
            it.tag = getSystemDirectoryInfo(filePath)
            clickListener.onClick(it)
        }).addItem(buildItemData(FileShowType.EXTERNAL_STORAGE_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getExternalStorageDirectory().absolutePath)
            clickListener.onClick(it)
        }).refreshList()
    }

    private fun buildItemData(
        showType: FileShowType, listener: View.OnClickListener
    ): CommonListItemData {
        return CommonListItemData.obj(showType.fileType, showType.info).clickListener(listener)
    }

    private fun getSystemDirectoryInfo(filePath: String): String {
        val fileSize = MemoryUtils.getMemorySize(filePath, false)
        return filePath + "  " + MemoryUtils.memorySizeFormat(fileSize)
    }
}