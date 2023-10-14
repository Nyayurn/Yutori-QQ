package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.event.message.created.PrivateOrGroupMessageCreatedEvent;

/**
 * @author Yurn
 */
public interface PrivateOrGroupMessageCreatedListener {
    /**
     * 触发事件
     *
     * @param event 事件信息
     * @param msg   过滤后的普通文本信息
     */
    void onMessageCreated(PrivateOrGroupMessageCreatedEvent event, String msg);
}
