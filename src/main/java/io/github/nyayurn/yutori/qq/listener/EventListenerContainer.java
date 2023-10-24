/*
Copyright (c) 2023 Yurn
yutori-qq is licensed under Mulan PSL v2.
You can use this software according to the terms and conditions of the Mulan PSL v2.
You may obtain a copy of Mulan PSL v2 at:
         http://license.coscl.org.cn/MulanPSL2
THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
See the Mulan PSL v2 for more details.
 */

package io.github.nyayurn.yutori.qq.listener;

import io.github.nyayurn.yutori.qq.entity.event.Bot;
import io.github.nyayurn.yutori.qq.event.guild.GuildEvent;
import io.github.nyayurn.yutori.qq.event.guild.GuildMemberEvent;
import io.github.nyayurn.yutori.qq.event.guild.GuildRoleEvent;
import io.github.nyayurn.yutori.qq.event.message.GroupMessageEvent;
import io.github.nyayurn.yutori.qq.event.message.MessageEvent;
import io.github.nyayurn.yutori.qq.event.message.PrivateMessageEvent;
import io.github.nyayurn.yutori.qq.event.user.FriendRequestEvent;
import io.github.nyayurn.yutori.qq.listener.guild.GuildAddedListener;
import io.github.nyayurn.yutori.qq.listener.guild.GuildRemovedListener;
import io.github.nyayurn.yutori.qq.listener.guild.GuildRequestListener;
import io.github.nyayurn.yutori.qq.listener.guild.GuildUpdatedListener;
import io.github.nyayurn.yutori.qq.listener.guild.member.GuildMemberAddedListener;
import io.github.nyayurn.yutori.qq.listener.guild.member.GuildMemberRemovedListener;
import io.github.nyayurn.yutori.qq.listener.guild.member.GuildMemberRequestListener;
import io.github.nyayurn.yutori.qq.listener.guild.member.GuildMemberUpdatedListener;
import io.github.nyayurn.yutori.qq.listener.guild.role.GuildRoleCreatedListener;
import io.github.nyayurn.yutori.qq.listener.guild.role.GuildRoleDeletedListener;
import io.github.nyayurn.yutori.qq.listener.guild.role.GuildRoleUpdatedListener;
import io.github.nyayurn.yutori.qq.listener.login.LoginAddedListener;
import io.github.nyayurn.yutori.qq.listener.login.LoginRemovedListener;
import io.github.nyayurn.yutori.qq.listener.login.LoginUpdatedListener;
import io.github.nyayurn.yutori.qq.listener.message.created.GroupMessageCreatedListener;
import io.github.nyayurn.yutori.qq.listener.message.created.MessageCreatedListener;
import io.github.nyayurn.yutori.qq.listener.message.created.PrivateMessageCreatedListener;
import io.github.nyayurn.yutori.qq.listener.message.deleted.GroupMessageDeletedListener;
import io.github.nyayurn.yutori.qq.listener.message.deleted.MessageDeletedListener;
import io.github.nyayurn.yutori.qq.listener.message.deleted.PrivateMessageDeletedListener;
import io.github.nyayurn.yutori.qq.listener.user.FriendRequestListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurn
 */
@Data
public class EventListenerContainer {
    public final List<GuildAddedListener> onGuildAddedListenerDelegate = new ArrayList<>();
    public final List<GuildUpdatedListener> onGuildUpdatedListenerDelegate = new ArrayList<>();
    public final List<GuildRemovedListener> onGuildRemovedListenerDelegate = new ArrayList<>();
    public final List<GuildRequestListener> onGuildRequestListenerDelegate = new ArrayList<>();

    public final List<GuildMemberAddedListener> onGuildMemberAddedListenerDelegate = new ArrayList<>();
    public final List<GuildMemberUpdatedListener> onGuildMemberUpdatedListenerDelegate = new ArrayList<>();
    public final List<GuildMemberRemovedListener> onGuildMemberRemovedListenerDelegate = new ArrayList<>();
    public final List<GuildMemberRequestListener> onGuildMemberRequestListenerDelegate = new ArrayList<>();

    public final List<GuildRoleCreatedListener> onGuildRoleCreatedListenerDelegate = new ArrayList<>();
    public final List<GuildRoleUpdatedListener> onGuildRoleUpdatedListenerDelegate = new ArrayList<>();
    public final List<GuildRoleDeletedListener> onGuildRoleDeletedListenerDelegate = new ArrayList<>();

    public final List<LoginAddedListener> onLoginAddedListenerDelegate = new ArrayList<>();
    public final List<LoginRemovedListener> onLoginRemovedListenerDelegate = new ArrayList<>();
    public final List<LoginUpdatedListener> onLoginUpdatedListenerDelegate = new ArrayList<>();

    public final List<MessageCreatedListener> onMessageCreatedListenerDelegate = new ArrayList<>();
    public final List<PrivateMessageCreatedListener> onPrivateMessageCreatedListenerDelegate = new ArrayList<>();
    public final List<GroupMessageCreatedListener> onGroupMessageCreatedListenerDelegate = new ArrayList<>();

    public final List<MessageDeletedListener> onMessageDeletedListenerDelegate = new ArrayList<>();
    public final List<PrivateMessageDeletedListener> onPrivateMessageDeletedListenerDelegate = new ArrayList<>();
    public final List<GroupMessageDeletedListener> onGroupMessageDeletedListenerDelegate = new ArrayList<>();

    public final List<FriendRequestListener> onFriendRequestListenerDelegate = new ArrayList<>();

    public void addOnGuildAddedListener(GuildAddedListener... listeners) {
        if (listeners != null) {
            onGuildAddedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildUpdatedListener(GuildUpdatedListener... listeners) {
        if (listeners != null) {
            onGuildUpdatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildRemovedListener(GuildRemovedListener... listeners) {
        if (listeners != null) {
            onGuildRemovedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildRequestListener(GuildRequestListener... listeners) {
        if (listeners != null) {
            onGuildRequestListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildMemberAddedListener(GuildMemberAddedListener... listeners) {
        if (listeners != null) {
            onGuildMemberAddedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildMemberUpdatedListener(GuildMemberUpdatedListener... listeners) {
        if (listeners != null) {
            onGuildMemberUpdatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildMemberRemovedListener(GuildMemberRemovedListener... listeners) {
        if (listeners != null) {
            onGuildMemberRemovedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildMemberRequestListener(GuildMemberRequestListener... listeners) {
        if (listeners != null) {
            onGuildMemberRequestListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildRoleCreatedListener(GuildRoleCreatedListener... listeners) {
        if (listeners != null) {
            onGuildRoleCreatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildRoleUpdatedListener(GuildRoleUpdatedListener... listeners) {
        if (listeners != null) {
            onGuildRoleUpdatedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnGuildRoleDeletedListener(GuildRoleDeletedListener... listeners) {
        if (listeners != null) {
            onGuildRoleDeletedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnLoginAddedListener(LoginAddedListener... listeners) {
        if (listeners != null) {
            onLoginAddedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnLoginRemovedListener(LoginRemovedListener... listeners) {
        if (listeners != null) {
            onLoginRemovedListenerDelegate.addAll(List.of(listeners));
        }
    }

    public void addOnLoginUpdatedListener(LoginUpdatedListener... listeners) {
        if (listeners != null) {
            onLoginUpdatedListenerDelegate.addAll(List.of(listeners));
        }
    }

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

    public void runOnGuildAddedListeners(Bot bot, GuildEvent event) {
        for (var listener : onGuildAddedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildUpdatedListeners(Bot bot, GuildEvent event) {
        for (var listener : onGuildUpdatedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildRemovedListeners(Bot bot, GuildEvent event) {
        for (var listener : onGuildRemovedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildRequestListeners(Bot bot, GuildEvent event) {
        for (var listener : onGuildRequestListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildMemberAddedListeners(Bot bot, GuildMemberEvent event) {
        for (var listener : onGuildMemberAddedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildMemberUpdatedListeners(Bot bot, GuildMemberEvent event) {
        for (var listener : onGuildMemberUpdatedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildMemberRemovedListeners(Bot bot, GuildMemberEvent event) {
        for (var listener : onGuildMemberRemovedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildMemberRequestListeners(Bot bot, GuildMemberEvent event) {
        for (var listener : onGuildMemberRequestListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildRoleCreatedListeners(Bot bot, GuildRoleEvent event) {
        for (var listener : onGuildRoleCreatedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildRoleUpdatedListeners(Bot bot, GuildRoleEvent event) {
        for (var listener : onGuildRoleUpdatedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnGuildRoleDeletedListeners(Bot bot, GuildRoleEvent event) {
        for (var listener : onGuildRoleDeletedListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }

    public void runOnLoginAddedListeners(Bot bot) {
        for (var listener : onLoginAddedListenerDelegate) {
            listener.onEvent(bot);
        }
    }

    public void runOnLoginRemovedListeners(Bot bot) {
        for (var listener : onLoginRemovedListenerDelegate) {
            listener.onEvent(bot);
        }
    }

    public void runOnLoginUpdatedListeners(Bot bot) {
        for (var listener : onLoginUpdatedListenerDelegate) {
            listener.onEvent(bot);
        }
    }

    public void runOnMessageCreatedListeners(Bot bot, MessageEvent event, String msg) {
        for (var listener : onMessageCreatedListenerDelegate) {
            listener.onEvent(bot, event, msg);
        }
    }

    public void runOnPrivateMessageCreatedListeners(Bot bot, PrivateMessageEvent event, String msg) {
        for (var listener : onPrivateMessageCreatedListenerDelegate) {
            listener.onEvent(bot, event, msg);
        }
    }

    public void runOnGroupMessageCreatedListeners(Bot bot, GroupMessageEvent event, String msg) {
        for (var listener : onGroupMessageCreatedListenerDelegate) {
            listener.onEvent(bot, event, msg);
        }
    }

    public void runOnMessageDeletedListeners(Bot bot, MessageEvent event, String msg) {
        for (var listener : onMessageDeletedListenerDelegate) {
            listener.onEvent(bot, event, msg);
        }
    }

    public void runOnPrivateMessageDeletedListeners(Bot bot, PrivateMessageEvent event, String msg) {
        for (var listener : onPrivateMessageDeletedListenerDelegate) {
            listener.onEvent(bot, event, msg);
        }
    }

    public void runOnGroupMessageDeletedListeners(Bot bot, GroupMessageEvent event, String msg) {
        for (var listener : onGroupMessageDeletedListenerDelegate) {
            listener.onEvent(bot, event, msg);
        }
    }

    public void runOnFriendRequestListeners(Bot bot, FriendRequestEvent event) {
        for (var listener : onFriendRequestListenerDelegate) {
            listener.onEvent(bot, event);
        }
    }
}
