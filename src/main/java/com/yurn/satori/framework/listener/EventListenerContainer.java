package com.yurn.satori.framework.listener;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.message.created.GroupMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.MessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateMessageCreatedEvent;
import com.yurn.satori.framework.event.user.FriendRequestEvent;
import com.yurn.satori.framework.listener.message.created.GroupMessageCreatedListener;
import com.yurn.satori.framework.listener.message.created.PrivateMessageCreatedListener;
import com.yurn.satori.framework.listener.message.created.MessageCreatedListener;
import com.yurn.satori.framework.listener.user.FriendRequestListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurn
 */
@Data
public class EventListenerContainer {
    public final List<MessageCreatedListener> onMessageCreatedListenerDelegate = new ArrayList<>();
    public final List<PrivateMessageCreatedListener> onPrivateMessageCreatedListenerDelegate = new ArrayList<>();
    public final List<GroupMessageCreatedListener> onGroupMessageCreatedListenerDelegate = new ArrayList<>();
    public final List<FriendRequestListener> onFriendRequestListenerDelegate = new ArrayList<>();

    public void addOnMessageCreatedListener(MessageCreatedListener listener) {
        onMessageCreatedListenerDelegate.add(listener);
    }

    public void addOnPrivateMessageCreatedListener(PrivateMessageCreatedListener listener) {
        onPrivateMessageCreatedListenerDelegate.add(listener);
    }

    public void addOnGroupMessageCreatedListener(GroupMessageCreatedListener listener) {
        onGroupMessageCreatedListenerDelegate.add(listener);
    }

    public void addOnFriendRequestListener(FriendRequestListener listener) {
        onFriendRequestListenerDelegate.add(listener);
    }

    public void runOnMessageCreatedListeners(Bot bot, MessageCreatedEvent event, String msg) {
        for (var listener : onMessageCreatedListenerDelegate) {
            listener.onMessageCreated(bot, event, msg);
        }
    }

    public void runOnPrivateMessageCreatedListeners(Bot bot, PrivateMessageCreatedEvent event, String msg) {
        for (var listener : onPrivateMessageCreatedListenerDelegate) {
            listener.onMessageCreated(bot, event, msg);
        }
    }

    public void runOnGroupMessageCreatedListeners(Bot bot, GroupMessageCreatedEvent event, String msg) {
        for (var listener : onGroupMessageCreatedListenerDelegate) {
            listener.onMessageCreated(bot, event, msg);
        }
    }

    public void runOnFriendRequestListeners(Bot bot, FriendRequestEvent event) {
        for (var listener : onFriendRequestListenerDelegate) {
            listener.onFriendRequest(bot, event);
        }
    }
}