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

package io.github.nyayurn.yutori.qq.listener.user;

import io.github.nyayurn.yutori.ListenerContainer;
import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.entity.PropertiesEntity;
import io.github.nyayurn.yutori.event.UserEvents;
import io.github.nyayurn.yutori.qq.entity.event.Bot;
import io.github.nyayurn.yutori.qq.event.user.FriendRequestEvent;
import io.github.nyayurn.yutori.qq.listener.EventListenerContainer;

/**
 * @author Yurn
 */
public class DispatcherUserListener {
    private final PropertiesEntity properties;
    private final EventListenerContainer listenerContainer;
    private final String platform;

    public DispatcherUserListener(String platform, PropertiesEntity properties, ListenerContainer listenerContainer,
                                  EventListenerContainer eventListenerContainer) {
        this.platform = platform;
        this.properties = properties;
        this.listenerContainer = eventListenerContainer;
        listenerContainer.addOnEventListener(event -> {
            if (event.getType().equals(UserEvents.FRIEND_REQUEST)) {
                onEvent(event);
            }
        });
    }

    private void onEvent(EventEntity event) {
        var bot = new Bot(platform, event.getSelfId(), properties);
        var newEvent = FriendRequestEvent.parse(event);
        listenerContainer.runOnFriendRequestListeners(bot, newEvent);
    }
}
