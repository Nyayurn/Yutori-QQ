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

package com.yurn.satori.framework.listener.user;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.user.FriendRequestEvent;
import com.yurn.satori.framework.listener.EventListenerContainer;
import com.yurn.satori.sdk.ListenerContainer;
import com.yurn.satori.sdk.api.MessageApi;
import com.yurn.satori.sdk.entity.EventEntity;
import com.yurn.satori.sdk.entity.PropertiesEntity;
import com.yurn.satori.sdk.entity.events.UserEvents;

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
        listenerContainer.addOnEventListener(this::onEvent);
    }

    private void onEvent(EventEntity event) {
        if (event.getType().equals(UserEvents.FRIEND_REQUEST)) {
            FriendRequestEvent newEvent = new FriendRequestEvent();
            MessageApi messageApi = new MessageApi(platform, event.getSelfId(), properties);
            listenerContainer.runOnFriendRequestListeners(new Bot(messageApi), newEvent);
        }
    }
}
