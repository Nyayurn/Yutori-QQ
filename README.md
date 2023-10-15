<div align="center">

# YurnQbotFramework

基于 [Satori](https://satori.js.org/zh-CN/) 协议和 [YurnSatoriFramework](https://github.com/Nyayurn/YurnSatoriFramework) 的 Java QQ 机器人开发框架

<img src="https://img.shields.io/badge/JDK-17+-brightgreen.svg?style=flat-square" alt="jdk-version">

</div>

# 快速开始

## 基础信息

> 提示: 本文档默认您了解并熟悉 Java 基本语法
> 
> 注意: 仅支持 JDK 17+

## 项目创建

1. 首先创建一个空的 Maven 项目(什么?不会?可以右上角关闭本页面了)
2. 依赖引入
3. 基本配置
4. 进阶

## 依赖引入

### Maven

- 在项目目录下新建 lib 文件夹
- 下载 jar 包并将其丢进 lib 文件夹内
- 配置 pom.xml

```xml
<dependencies>
    <!-- 核心框架 -->
    <dependency>
        <groupId>com.yurn</groupId>
        <artifactId>YurnQbotFramework</artifactId>
        <version>0.0.2</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/lib/YurnQbotFramework-0.0.2.jar</systemPath>
    </dependency>
    <dependency>
        <groupId>com.yurn</groupId>
        <artifactId>YurnSatoriFramework</artifactId>
        <version>0.0.3</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/lib/YurnSatoriFramework-0.0.3.jar</systemPath>
    </dependency>
    <!-- Http 和 WebSocket 所需依赖 -->
    <dependency>
        <artifactId>okhttp</artifactId>
        <groupId>com.squareup.okhttp3</groupId>
        <version>4.10.0</version>
    </dependency>
    <!-- 日志系统(可替换为其他slf4j实现) -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
    <!-- JSON 序列化/反序列化 -->
    <dependency>
        <groupId>com.alibaba.fastjson2</groupId>
        <artifactId>fastjson2</artifactId>
        <version>2.0.40</version>
    </dependency>
    <!-- HTML 解析 -->
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.16.1</version>
    </dependency>
</dependencies>
```

## 基本配置

### 启动类

```java
public class Main {
    static {
        // new 一个对象以触发监听器的注册
        new TestListener();
    }

    public static void main(String[] args) {
        // 初始化核心启动类, 并运行
        new Boot("127.0.0.1:5500", "token").run();
    }
}
```

### 第一个监听器

```java
public class TestListener implements MessageCreatedListener {
    public TestListener() {
        // 通过在构造器内对 ListenerContainer 添加一个事件实现注册
        ListenerContainer.addMessageCreatedListener(this);
    }

    @Override
    public void onMessageCreated(MessageCreatedEvent event, String msg) {
        if ("test".equals(msg)) {
            // 通过 event 获取 channel 对象, 并往该 channel 发送一条消息
            event.getChannel().createMessage("test done!");
        }
    }
}
```

# 进阶

- 请参考 [Satori 文档](https://satori.js.org/zh-CN/protocol)
- 源码含有 javadoc 方便阅读, 请自行阅读源码
