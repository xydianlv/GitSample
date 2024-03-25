@file:JvmName("GS")
// 在 Kotlin 中，无需通过类名可调用的方法，称为顶级函数
// 在 Kotlin 类中，可直接通过函数名调用顶级函数
// 在 Java 类中，需要通过 「文件名+kt」 来调用该类中的顶级函数
// 像该类中的方法，需要通过 GSTopLevelFunctionTestkt.appName() 来调用
// 也可以通过注释 @file:JvmName("GS") 自定义调用名称
// 在该类中，加了 @file:JvmName("GS") 注释后，调用方法就变成了 GS.appName()
package com.example.gitsample.system.kotlin.tlfunction

fun appName(): String {
    return "GitSample"
}