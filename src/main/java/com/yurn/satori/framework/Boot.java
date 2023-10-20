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

package com.yurn.satori.framework;

import com.yurn.satori.framework.listener.EventListenerContainer;
import com.yurn.satori.framework.listener.message.created.DispatcherMessageCreatedListener;
import com.yurn.satori.framework.listener.message.deleted.DispatcherMessageDeletedListener;
import com.yurn.satori.framework.listener.user.DispatcherUserListener;
import com.yurn.satori.sdk.ListenerContainer;
import com.yurn.satori.sdk.MyWebSocketClient;
import com.yurn.satori.sdk.entity.PropertiesEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

/**
 * @author Yurn
 */
@Slf4j
@Getter
public class Boot implements Runnable {
    private final ListenerContainer listenerContainer = new ListenerContainer();
    private final PropertiesEntity properties;

    public Boot(String address, String token, EventListenerContainer eventListenerContainer) {
        this("chronocat", address, token, eventListenerContainer);
    }

    public Boot(String platform, String address, String token, EventListenerContainer eventListenerContainer) {
        properties = new PropertiesEntity(address, token);
        new DispatcherUserListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherMessageCreatedListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherMessageDeletedListener(platform, properties, listenerContainer, eventListenerContainer);
    }

    @Override
    public void run() {
        try {
            // 新建一个 WebSocket 连接
            new MyWebSocketClient(properties.getAddress(), properties.getToken(), listenerContainer).connect();
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
