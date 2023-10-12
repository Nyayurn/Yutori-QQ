package com.yurn.satori.framework.listener.user;

import com.yurn.satori.framework.event.user.FriendRequestEvent;
import com.yurn.satori.sdk.GlobalEventChannel;
import com.yurn.satori.sdk.entity.*;
import com.yurn.satori.sdk.entity.events.UserEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Yurn
 */
@Component
public class DispatcherUserListener {
    private final FriendRequestListener[] friendRequestListeners;

    @Autowired
    public DispatcherUserListener(@Nullable FriendRequestListener[] friendRequestListeners) {
        this.friendRequestListeners = friendRequestListeners;
        GlobalEventChannel.INSTANCE.addEvent(this::onEvent);
    }

    @SuppressWarnings("DuplicatedCode")
    private void onEvent(EventEntity event) {
        if (event.getType().equals(UserEvents.FRIEND_REQUEST)) {
            FriendRequestEvent newEvent = new FriendRequestEvent();
            if (friendRequestListeners != null) {
                for (var listener : friendRequestListeners) {
                    listener.onFriendRequest(newEvent);
                }
            }
        }
    }
}
