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

package io.github.nyayurn.yutori.qq.listener.guild.role;

import io.github.nyayurn.yutori.ListenerContainer;
import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.entity.PropertiesEntity;
import io.github.nyayurn.yutori.event.GuildRoleEvents;
import io.github.nyayurn.yutori.qq.entity.event.Bot;
import io.github.nyayurn.yutori.qq.event.guild.GuildRoleEvent;
import io.github.nyayurn.yutori.qq.listener.EventListenerContainer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yurn
 */
@Slf4j
public class DispatcherGuildRoleListener {
    private final PropertiesEntity properties;
    private final EventListenerContainer listenerContainer;
    private final String platform;

    public DispatcherGuildRoleListener(String platform, PropertiesEntity properties, ListenerContainer listenerContainer,
                                       EventListenerContainer eventListenerContainer) {
        this.platform = platform;
        this.properties = properties;
        this.listenerContainer = eventListenerContainer;
        listenerContainer.addOnEventListener(event -> {
            switch (event.getType()) {
                case GuildRoleEvents.GUILD_ROLE_CREATED -> onCreated(event);
                case GuildRoleEvents.GUILD_ROLE_UPDATED -> onUpdated(event);
                case GuildRoleEvents.GUILD_ROLE_DELETED -> onDeleted(event);
                default -> {}
            }
        });
    }

    private void onCreated(EventEntity event) {
        var bot = new Bot(platform, event.getSelfId(), properties);
        var newEvent = GuildRoleEvent.parse(event);
        listenerContainer.runOnGuildRoleCreatedListeners(bot, newEvent);
    }

    private void onUpdated(EventEntity event) {
        var bot = new Bot(platform, event.getSelfId(), properties);
        var newEvent = GuildRoleEvent.parse(event);
        listenerContainer.runOnGuildRoleUpdatedListeners(bot, newEvent);
    }

    private void onDeleted(EventEntity event) {
        var bot = new Bot(platform, event.getSelfId(), properties);
        var newEvent = GuildRoleEvent.parse(event);
        listenerContainer.runOnGuildRoleDeletedListeners(bot, newEvent);
    }
}
