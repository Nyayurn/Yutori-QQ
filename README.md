<div align="center">

# YurnQbotFramework

基于 [Satori](https://satori.js.org/zh-CN/) 协议和 [YurnSatoriFramework](https://github.com/Nyayurn/YurnSatoriFramework) 的 Java QQ 机器人开发框架

<img src="https://img.shields.io/badge/JDK-17+-brightgreen.svg?style=flat-square" alt="jdk-version">

</div>

# 快速开始

## 基础信息

> 提示: 本文档默认您了解并熟悉 Java 基本语法以及 SpringBoot 开发体系

> 注意: 仅支持 JDK 17+ 与 SpringBoot 3.0.0+

## 项目创建

1. 首先创建一个空的 SpringBoot 项目(什么?不会?可以右上角关闭本页面了)
2. 依赖引入
3. 第一个监听器
4. 进阶

## 依赖引入

### Maven

- 在项目目录下新建 lib 文件夹
- 下载 jar 包并将其丢进 lib 文件夹内
- 配置 pom.xml

```xml
<dependency>
    <groupId>com.yurn</groupId>
    <artifactId>YurnQbotFramework</artifactId>
    <version>0.0.1</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/YurnQbotFramework-0.0.1.jar</systemPath>
</dependency>
```

## 第一个监听器

```java
@Component
public class MyMessageCreatedListener implements PrivateOrGroupMessageCreatedListener {
    public void onMessage(PrivateOrGroupMessageCreatedEvent event, String msg) {
        if (msg.contains("原神")) {
            event.getChannel().createMessage("我超, OP!");
        }
    }
}
```

# 进阶

- 源码含有 javadoc 方便阅读, 请自行阅读源码

## 打包

- 修改 pom.xml

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <includeSystemScope>true</includeSystemScope>
    </configuration>
</plugin>
```