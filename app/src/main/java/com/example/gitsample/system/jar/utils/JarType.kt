package com.example.gitsample.system.jar.utils

enum class JarType(
    val fileName: String,
    val classPath: String
) {

    JAR_JAVA(
        "jarTest.jar",
        "com/example/gitsample/system/jar/build/JarLoaderImpl"
    ),
    JAR_JAVA_DEX(
        "jarTest-dex.jar",
        "com/example/gitsample/system/jar/build/JarLoaderImpl"
    ),
    JAR_KOTLIN(
        "testKotlin.jar",
        "com/example/gitsample/system/jar/test/JarLoaderImpl"
    );
}