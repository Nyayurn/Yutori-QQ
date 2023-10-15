package com.yurn.satori.framework.listener.user;

import com.yurn.satori.framework.event.user.FriendRequestEvent;
import com.yurn.satori.framework.listener.ListenerContainer;
import com.yurn.satori.sdk.GlobalEventChannel;
import com.yurn.satori.sdk.entity.EventEntity;
import com.yurn.satori.sdk.entity.events.UserEvents;

import java.util.List;

/**
 * @author Yurn
 */
public class DispatcherUserListener {

    public DispatcherUserListener() {
        GlobalEventChannel.getINSTANCE().addEvent(this::onEvent);
    }

    private void onEvent(EventEntity event) {
        if (event.getType().equals(UserEvents.FRIEND_REQUEST)) {
            FriendRequestEvent newEvent = new FriendRequestEvent();
            List<FriendRequestListener> friendRequestListenerList = ListenerContainer.getINSTANCE().FRIEND_REQUEST_LISTENER_LIST;
            for (var listener : friendRequestListenerList) {
                listener.onFriendRequest(newEvent);
            }
        }
    }
}
