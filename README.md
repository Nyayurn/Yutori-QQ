<div align="center">

# YurnQbotFramework

基于 [Satori](https://satori.js.org/zh-CN/) 协议和 [YurnSatoriFramework](https://github.com/Nyayurn/YurnSatoriFramework) 的 Java QQ 机器人开发框架

<img src="https://img.shields.io/badge/JDK-17+-brightgreen.svg?style=flat-square" alt="jdk-version">

</div>

# 快速开始

## 基础信息

> 提示: 本文档默认您了解并熟悉 Java 基本语法

## 项目创建

1. 首先创建一个空的 Maven 项目(什么?不会?可以右上角关闭本页面了)
2. 依赖引入
3. 基本配置
4. 进阶

## 依赖引入

### Maven

```xml
<dependencies>
    <!-- 核心框架 -->
    <dependency>
        <groupId>com.yurn</groupId>
        <artifactId>YurnQbotFramework</artifactId>
        <version>0.0.6</version>
    </dependency>
    <!-- 日志系统(可替换为其他slf4j实现) -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
</dependencies>
```

## 基本配置

### 启动类

```java
public class Main {
    private final EventListenerContainer listenerContainer = new EventListenerContainer();

    public Main() {
        // new 一个对象以触发监听器的注册
        new TestListener(listenerContainer);
    }

    private void run() {
        // 初始化核心启动类, 并运行, platform参数可缺省, 默认"chronocat"
        //new Boot("red", "127.0.0.1:5500", "token", listenerContainer).run()
        new Boot("127.0.0.1:5500", "token", listenerContainer).run();
    }
    
    public static void main(String[] args) {
        new Main().run();
    }
}
```

### 第一个监听器

```java
public class TestListener implements MessageCreatedListener {
    public TestListener(EventListenerContainer eventListenerContainer) {
        // 通过在构造器内对 ListenerContainer 添加一个事件实现注册
        eventListenerContainer.addOnMessageCreatedListener(this);
    }

    @Override
    public void onMessageCreated(Bot bot, MessageCreatedEvent event, String msg) {
        if ("test".equals(msg)) {
            // 通过 bot 对象创建消息
            bot.createMessage(event.getChannel(), "test done!");
        }
    }
}
```

# 进阶

- 请参考 [Satori 文档](https://satori.js.org/zh-CN/protocol)
- 源码含有 javadoc 方便阅读, 请自行阅读源码
