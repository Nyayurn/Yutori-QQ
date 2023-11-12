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

package io.github.nyayurn.yutori.qq;

import io.github.nyayurn.yutori.qq.listener.EventListenerContainer;
import io.github.nyayurn.yutori.qq.listener.guild.DispatcherGuildListener;
import io.github.nyayurn.yutori.qq.listener.guild.member.DispatcherGuildMemberListener;
import io.github.nyayurn.yutori.qq.listener.guild.role.DispatcherGuildRoleListener;
import io.github.nyayurn.yutori.qq.listener.login.DispatcherLoginListener;
import io.github.nyayurn.yutori.qq.listener.message.DispatcherMessageListener;
import io.github.nyayurn.yutori.qq.listener.user.DispatcherUserListener;
import io.github.nyayurn.yutori.ListenerContainer;
import io.github.nyayurn.yutori.MyWebSocketClient;
import io.github.nyayurn.yutori.entity.PropertiesEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

/**
 * @author Yurn
 */
@Slf4j
@Getter
public class Boot {
    private final ListenerContainer listenerContainer = new ListenerContainer();
    private final PropertiesEntity properties;
    private MyWebSocketClient myWebSocketClient;

    public Boot(String address, String token, EventListenerContainer eventListenerContainer) {
        this("chronocat", address, token, eventListenerContainer);
    }

    public Boot(String platform, String address, String token, EventListenerContainer eventListenerContainer) {
        properties = new PropertiesEntity(address, token);
        new DispatcherGuildMemberListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherGuildRoleListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherGuildListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherLoginListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherMessageListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherUserListener(platform, properties, listenerContainer, eventListenerContainer);
    }

    public void run() {
        try {
            myWebSocketClient = new MyWebSocketClient(properties.getAddress(), properties.getToken(), listenerContainer);
            myWebSocketClient.connect();
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public void cancel() {
        myWebSocketClient.close();
    }
}
