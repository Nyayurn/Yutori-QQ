package com.yurn.satori.framework.listener;

import com.yurn.satori.framework.listener.message.created.GroupMessageCreatedListener;
import com.yurn.satori.framework.listener.message.created.PrivateMessageCreatedListener;
import com.yurn.satori.framework.listener.message.created.MessageCreatedListener;
import com.yurn.satori.framework.listener.user.FriendRequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurn
 */
public class ListenerContainer {
    public static final List<MessageCreatedListener> MESSAGE_CREATED_LISTENER_LIST = new ArrayList<>();
    public static final List<PrivateMessageCreatedListener> PRIVATE_MESSAGE_CREATED_LISTENER_LIST = new ArrayList<>();
    public static final List<GroupMessageCreatedListener> GROUP_MESSAGE_CREATED_LISTENER_LIST = new ArrayList<>();
    public static final List<FriendRequestListener> FRIEND_REQUEST_LISTENER_LIST = new ArrayList<>();

    public static void addMessageCreatedListener(MessageCreatedListener listener) {
        MESSAGE_CREATED_LISTENER_LIST.add(listener);
    }

    public static void addPrivateMessageCreatedListener(PrivateMessageCreatedListener listener) {
        PRIVATE_MESSAGE_CREATED_LISTENER_LIST.add(listener);
    }

    public static void addGroupMessageCreatedListener(GroupMessageCreatedListener listener) {
        GROUP_MESSAGE_CREATED_LISTENER_LIST.add(listener);
    }

    public static void addFriendRequestListener(FriendRequestListener listener) {
        FRIEND_REQUEST_LISTENER_LIST.add(listener);
    }
}
