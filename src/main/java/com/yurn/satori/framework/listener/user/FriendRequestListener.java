package com.yurn.satori.framework.listener.user;

import com.yurn.satori.framework.event.user.FriendRequestEvent;

/**
 * @author Yurn
 */
public interface FriendRequestListener {
    /**
     * 触发事件
     *
     * @param event 事件信息
     */
    void onFriendRequest(FriendRequestEvent event);
}
