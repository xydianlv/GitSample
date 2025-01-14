Android 生成 .jar 文件，并动态加载流程

# Java 代码封装 .jar 文件流程

* 在 /jar/ 目录下，执行命令
  $ javac -d out build/*.java
  将 /build/ 目录下的所有 .java 文件，构建为 out/com/example/gitsample/system/jar/build/ 目录中的 .class 文件

* 在 /jar/ 目录下，执行命令
  $ jar cvf jarTest.jar -C out/ .
  将 /out/ 目录中的 .class 文件，打包到 /jar/jarTest.jar 文件中

* 在 /jar/ 目录下，执行命令
  $ d8 --output jarTest-dex.jar jarTest.jar
  将 /jar/jarTest.jar 文件，构建为 dex 模式的 /jar/jarTest-dex.jar 文件

* 可通过命令
  $ jar tf jarTest.jar
  查看 .jar 文件中的类，例如 “com/example/gitsample/system/jar/build/JarLoaderImpl.class”

# Kotlin 代码封装 .jar 文件流程

* 在 /jar/ 目录下，执行命令
  $ kotlinc test/*.kt -d out
  将 /test/ 目录下的所有 .kt 文件，构建为 out/com/example/gitsample/system/jar/test/ 目录中的 .class 文件
  注意：kotlinc 命令，需要配置本地环境变量

* 在 /jar/ 目录下，执行命令
  $ jar cvf testKotlin.jar -C out/ .
  将 /out/ 目录中的 .class 文件，打包到 /jar/testKotlin.jar 文件中

* 在 /jar/ 目录下，执行命令
  $ d8 --output testKotlin-dex.jar jarTest.jar
  将 /jar/testKotlin.jar 文件，构建为 dex 模式的 /jar/testKotlin-dex.jar 文件

* 可通过命令
  $ jar tf jarTest.jar
  查看 .jar 文件中的类，例如 “com/example/gitsample/system/jar/build/JarLoaderImpl.class”


# 动态加载 .jar 文件流程

* 将生成的 jarTest.jar 或 jarTest-dex.jar 文件，传递到存储目录中

* 创建 JarLoadUtils 工具类，执行流程

**  获取 .jar 文件
**  通过 DexClassLoader 加载 .jar 文件
**  根据类名获取 class 对象（ 注意！此处的类名，不能包含 .class / .java 后缀 ）
**  通过 class 对象生成类的实例对象
**  执行方法

* 在动态加载 .jar 文件时，jarTest.jar 文件与 jarTest-dex.jar 功能一样，都可成功加载