package com.yurn.satori.framework.listener;

import com.yurn.satori.framework.listener.message.created.GroupMessageCreatedListener;
import com.yurn.satori.framework.listener.message.created.PrivateMessageCreatedListener;
import com.yurn.satori.framework.listener.message.created.MessageCreatedListener;
import com.yurn.satori.framework.listener.user.FriendRequestListener;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurn
 */
@Data
public class ListenerContainer {
    @Getter
    private static final ListenerContainer INSTANCE = new ListenerContainer();

    public final List<MessageCreatedListener> MESSAGE_CREATED_LISTENER_LIST = new ArrayList<>();
    public final List<PrivateMessageCreatedListener> PRIVATE_MESSAGE_CREATED_LISTENER_LIST = new ArrayList<>();
    public final List<GroupMessageCreatedListener> GROUP_MESSAGE_CREATED_LISTENER_LIST = new ArrayList<>();
    public final List<FriendRequestListener> FRIEND_REQUEST_LISTENER_LIST = new ArrayList<>();

    public void addMessageCreatedListener(MessageCreatedListener listener) {
        MESSAGE_CREATED_LISTENER_LIST.add(listener);
    }

    public void addPrivateMessageCreatedListener(PrivateMessageCreatedListener listener) {
        PRIVATE_MESSAGE_CREATED_LISTENER_LIST.add(listener);
    }

    public void addGroupMessageCreatedListener(GroupMessageCreatedListener listener) {
        GROUP_MESSAGE_CREATED_LISTENER_LIST.add(listener);
    }

    public void addFriendRequestListener(FriendRequestListener listener) {
        FRIEND_REQUEST_LISTENER_LIST.add(listener);
    }
}
