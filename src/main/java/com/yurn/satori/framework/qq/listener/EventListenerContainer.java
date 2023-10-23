/*
Copyright (c) 2023 Yurn
YurnQbotFramework is licensed under Mulan PSL v2.
You can use this software according to the terms and conditions of the Mulan PSL v2.
You may obtain a copy of Mulan PSL v2 at:
         http://license.coscl.org.cn/MulanPSL2
THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
See the Mulan PSL v2 for more details.
 */

package com.yurn.satori.framework.qq.listener;

import com.yurn.satori.framework.qq.entity.event.Bot;
import com.yurn.satori.framework.qq.event.message.created.GroupMessageCreatedEvent;
import com.yurn.satori.framework.qq.event.message.created.MessageCreatedEvent;
import com.yurn.satori.framework.qq.event.message.created.PrivateMessageCreatedEvent;
import com.yurn.satori.framework.qq.event.message.deleted.GroupMessageDeletedEvent;
import com.yurn.satori.framework.qq.event.message.deleted.MessageDeletedEvent;
import com.yurn.satori.framework.qq.event.message.deleted.PrivateMessageDeletedEvent;
import com.yurn.satori.framework.qq.event.user.FriendRequestEvent;
import com.yurn.satori.framework.qq.listener.message.created.GroupMessageCreatedListener;
import com.yurn.satori.framework.qq.listener.message.created.PrivateMessageCreatedListener;
import com.yurn.satori.framework.qq.listener.message.created.MessageCreatedListener;
import com.yurn.satori.framework.qq.listener.message.deleted.GroupMessageDeletedListener;
import com.yurn.satori.framework.qq.listener.message.deleted.MessageDeletedListener;
import com.yurn.satori.framework.qq.listener.message.deleted.PrivateMessageDeletedListener;
import com.yurn.satori.framework.qq.listener.user.FriendRequestListener;
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
    public final List<MessageDeletedListener> onMessageDeletedListenerDelegate = new ArrayList<>();
    public final List<PrivateMessageDeletedListener> onPrivateMessageDeletedListenerDelegate = new ArrayList<>();
    public final List<GroupMessageDeletedListener> onGroupMessageDeletedListenerDelegate = new ArrayList<>();
    public final List<FriendRequestListener> onFriendRequestListenerDelegate = new ArrayList<>();

    public void addOnMessageCreatedListener(MessageCreatedListener... listeners) {
        if (listeners != null) {
            onMessageCreatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnPrivateMessageCreatedListener(PrivateMessageCreatedListener... listeners) {
        if (listeners != null) {
            onPrivateMessageCreatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGroupMessageCreatedListener(GroupMessageCreatedListener... listeners) {
        if (listeners != null) {
            onGroupMessageCreatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnMessageDeletedListener(MessageDeletedListener... listeners) {
        if (listeners != null) {
            onMessageDeletedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnPrivateMessageDeletedListener(PrivateMessageDeletedListener... listeners) {
        if (listeners != null) {
            onPrivateMessageDeletedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGroupMessageDeletedListener(GroupMessageDeletedListener... listeners) {
        if (listeners != null) {
            onGroupMessageDeletedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnFriendRequestListener(FriendRequestListener... listeners) {
        if (listeners != null) {
            onFriendRequestListenerDelegate.addAll(List.of(listeners));
        }
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

    public void runOnMessageDeletedListeners(Bot bot, MessageDeletedEvent event, String msg) {
        for (var listener : onMessageDeletedListenerDelegate) {
            listener.onMessageCreated(bot, event, msg);
        }
    }

    public void runOnPrivateMessageDeletedListeners(Bot bot, PrivateMessageDeletedEvent event, String msg) {
        for (var listener : onPrivateMessageDeletedListenerDelegate) {
            listener.onMessageCreated(bot, event, msg);
        }
    }

    public void runOnGroupMessageDeletedListeners(Bot bot, GroupMessageDeletedEvent event, String msg) {
        for (var listener : onGroupMessageDeletedListenerDelegate) {
            listener.onMessageCreated(bot, event, msg);
        }
    }

    public void runOnFriendRequestListeners(Bot bot, FriendRequestEvent event) {
        for (var listener : onFriendRequestListenerDelegate) {
            listener.onFriendRequest(bot, event);
        }
    }
}
