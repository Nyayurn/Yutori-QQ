<div align="center">

# Yutori-QQ

基于 [Satori](https://satori.js.org/zh-CN/) 协议和 [Yutori 0.1.0](https://github.com/Nyayurn/Yutori/tree/0.1.0) 的 Java QQ 机器人开发框架

[Yutori 新版本](https://github.com/Nyayurn/Yutori) 已能替代该项目

<img src="https://img.shields.io/badge/JDK-17+-brightgreen.svg?style=flat-square" alt="jdk-version">

</div>

# 快速开始

1. 创建一个 Maven 或 Gradle 项目
2. 依赖引入
3. 基本配置
4. 进阶

## 依赖引入

### Maven

```xml

<dependencies>
    <dependency>
        <groupId>io.github.nyayurn</groupId>
        <artifactId>yutori-qq</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

### Gradle

```kotlin
implementation("io.github.nyayurn:yutori-qq:0.1.0")
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
    public void onEvent(Bot bot, MessageEvent event, String msg) {
        if ("test".equals(msg)) {
            // 通过 bot 对象创建消息
            bot.getMessageApi().createMessage(event.getChannel().getId(), "test done!");
        }
    }
}
```

# 进阶

- 请参考 [Satori 文档](https://satori.js.org/zh-CN/protocol)
- 源码含有 javadoc 方便阅读, 请自行阅读源码

|           Listener            |      描述      |
|:-----------------------------:|:------------:|
|      GuildAddedListener       |   监听加入群组事件   |
|     GuildUpdatedListener      |  监听群组被修改事件   |
|      GuildRemoveListener      |   监听退出群组事件   |
|     GuildRequestListener      |  监听新的入群邀请事件  |
|   GuildMemberAddedListener    |  监听群组成员增加事件  |
|  GuildMemberUpdatedListener   | 监听群组成员信息更新事件 |
|  GuildMemberRemovedListener   |  监听群组成员移除事件  |
|  GuildMemberRequestListener   |  监听新的加群请求事件  |
|   GuildRoleCreatedListener    |  监听群组角色创建事件  |
|   GuildRoleUpdatedListener    |  监听群组角色修改事件  |
|   GuildRoleDeletedListener    |  监听群组角色删除事件  |
|      LoginAddedListener       |   监听登录创建事件   |
|     LoginRemovedListener      |   监听登录删除事件   |
|     LoginUpdatedListener      |  监听登录信息更新事件  |
|    MessageCreatedListener     |   监听消息创建事件   |
|  GroupMessageCreatedListener  |  监听群消息创建事件   |
| PrivateMessageCreatedListener |  监听私聊消息创建事件  |
|    MessageDeletedListener     |   监听消息删除事件   |
|  GroupMessageDeletedListener  |  监听群消息删除事件   |
| PrivateMessageDeletedListener |  监听私聊消息删除事件  |
|     FriendRequestListener     |  监听新的好友请求事件  |

| MessageElement |              描述               |
|:--------------:|:-----------------------------:|
|   AtElement    | 提及用户<br>可用静态方法atUser和atType构建 |
|   BrElement    |              换行               |
| MessageElement |              消息               |
|  TextElement   |              纯文本              |
|  AudioElement  |              语音               |
|  FileElement   |              文件               |
|   ImgElement   |              图片               |
|  VideoElement  |              视频               |

# 迁移

如果你想从 0.0.9 之前的版本迁移到 0.0.9, 请按如下步骤:
1. 删除所有的 import, 并重新导入(注意: 应导入带有 qq 字眼的包)
2. 修改所有监听器的重写的方法名为 onEvent
3. 修改所有消息相关的监听器的 onEvent 方法的参数, 如 MessageCreatedEvent 修改为 MessageEvent
4. 修改发消息为 bot.getMessageApi().createMessage()
