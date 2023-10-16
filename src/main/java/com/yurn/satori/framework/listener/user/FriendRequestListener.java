package com.yurn.satori.framework.listener.user;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.user.FriendRequestEvent;

/**
 * @author Yurn
 */
public interface FriendRequestListener {
    /**
     * 触发事件
     *
     * @param bot   机器人信息
     * @param event 事件信息
     */
    void onFriendRequest(Bot bot, FriendRequestEvent event);
}
