package com.example.gitsample.system.file

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.content.ContextCompat
import com.example.gitsample.R
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityFileManagerBinding
import com.example.gitsample.utils.MemoryUtils
import com.example.gitsample.widget.list.CommonListItemData

class ActivityFileManager : BaseActivity() {

    companion object {
        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityFileManager::class.java))
        }
    }

    private lateinit var binding: ActivityFileManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initView()
        initList()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.FILE_MANAGER.title)
    }

    private fun initView() {
        binding.alert.setOnClickListener {
            binding.alert.text = resources.getText(R.string.path_manager_alert)
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
        binding.list.addItem(CommonListItemData.buildData(FileShowType.INNER_DATA_PATH) {
            it.tag = PathManager.manager().innerDataPath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.INNER_CACHE_PATH) {
            it.tag = PathManager.manager().innerCachePath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.EXTRA_FILE_PATH) {
            it.tag = PathManager.manager().extraFilePath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.EXTRA_CACHE_PATH) {
            it.tag = PathManager.manager().extraCachePath(FileType.ROOT)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.MEDIA_PATH) {
            it.tag = PathManager.manager().saveMediaPath()
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.ROOT_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getRootDirectory().absolutePath)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.DATA_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getDataDirectory().absolutePath)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.DOWNLOAD_CACHE_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getDownloadCacheDirectory().absolutePath)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.STORAGE_DIRECTORY) {
            val filePath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Environment.getStorageDirectory().absolutePath
            } else {
                Environment.getExternalStorageDirectory().absolutePath
            }
            it.tag = getSystemDirectoryInfo(filePath)
            clickListener.onClick(it)
        }).addItem(CommonListItemData.buildData(FileShowType.EXTERNAL_STORAGE_DIRECTORY) {
            it.tag = getSystemDirectoryInfo(Environment.getExternalStorageDirectory().absolutePath)
            clickListener.onClick(it)
        }).refreshList()
    }

    private fun getSystemDirectoryInfo(filePath: String): String {
        val fileSize = MemoryUtils.getMemorySize(filePath, false)
        return filePath + "  " + MemoryUtils.memorySizeFormat(fileSize)
    }
}