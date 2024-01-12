package com.example.gitsample.system.file

enum class FileShowType(val fileType: String, val info: String) {

    INNER_DATA_PATH("InnerDataPath", "私有存储空间中，存放数据的路径"),
    INNER_CACHE_PATH("InnerCachePath", "私有存储空间中，存放缓存的路径"),
    EXTRA_FILE_PATH("ExtraFilePath", "SD卡文件存放路径"),
    EXTRA_CACHE_PATH("ExtraCachePath", "SD卡缓存文件路径"),
    MEDIA_PATH("MediaPath", "DCIM 中创建的存放目录"),

    ROOT_DIRECTORY("RootDirectory", "系统目录下的 /system 路径"),
    STORAGE_DIRECTORY("StorageDirectory", "系统目录下的 /storage 路径"),
    DATA_DIRECTORY("DataDirectory", "系统目录下的 /data 路径"),
    DOWNLOAD_CACHE_DIRECTORY("DownloadCacheDirectory", "系统目录下的 /data/cache 路径"),
    EXTERNAL_STORAGE_DIRECTORY("ExternalStorageDirectory", "系统目录下的 /storage/emulated/0 路径")
}