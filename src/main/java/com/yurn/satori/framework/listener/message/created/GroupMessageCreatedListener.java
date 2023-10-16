package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.message.created.GroupMessageCreatedEvent;

/**
 * @author Yurn
 */
public interface GroupMessageCreatedListener {
    /**
     * 触发事件
     *
     * @param bot   机器人信息
     * @param event 事件信息
     * @param msg   过滤后的普通文本信息
     */
    void onMessageCreated(Bot bot, GroupMessageCreatedEvent event, String msg);
}
