package com.example.gitsample.system.file

enum class FileShowType(val fileType: String, val info: String) {

    INNER_DATA_PATH("InnerDataPath", "私有存储空间中，存放数据的路径"),
    INNER_CACHE_PATH("InnerCachePath", "私有存储空间中，存放缓存的路径"),
    EXTRA_FILE_PATH("ExtraFilePath", "SD卡文件存放路径"),
    EXTRA_CACHE_PATH("ExtraCachePath", "SD卡缓存文件路径"),
    MEDIA_PATH("MediaPath", "DCIM 中创建的存放目录")
}