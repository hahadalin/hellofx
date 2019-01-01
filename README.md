# 最简单的JavaFx11项目骨架

* 用maven管理javafx11依赖，比自己手动设置module方便很多
* 运行需要使用命令``mvn clean compile package exec:java``，顺便会打个jar包
* 创建runtime image：![创建runtime image](doc/create%20runtime%20image.jpg)
* 这样的runtime image运行时需要指定main class，因为hellofx中没有指定main class。可以用这个方法指定：
    1. 执行命令``mvn clean compile exec:java``，编译出classes
    2. 执行命令``jmod create --module-version 1.0 --main-class cn.dalin.Main --class-path target/classes jmods/hellofx.jmod``，
    创建hellofx.jmod到jmods文件夹（如果不存在需要mkdir）。这个hellofx.jmod就是这个项目独立的module，使用jlink与依赖链接后就可以跑起来。
    3. 执行命令``jlink -p "%PATH_TO_FX_MODS%;jmods" --add-modules=hellofx --output jre``，创建runtime image到jre目录。
    4. 现在就可以直接``jre/bin/java -m hellofx``来启动程序了。

参考：[openjfx-docs](https://openjfx.io/openjfx-docs/#IDE-Intellij)
