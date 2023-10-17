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

package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.message.created.GroupMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.MessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateMessageCreatedEvent;
import com.yurn.satori.framework.listener.EventListenerContainer;
import com.yurn.satori.framework.message.element.BaseMessageElement;
import com.yurn.satori.framework.message.element.basic.AtElement;
import com.yurn.satori.framework.message.element.basic.TextElement;
import com.yurn.satori.framework.message.element.resource.AudioElement;
import com.yurn.satori.framework.message.element.resource.FileElement;
import com.yurn.satori.framework.message.element.resource.ImgElement;
import com.yurn.satori.framework.message.element.resource.VideoElement;
import com.yurn.satori.sdk.ListenerContainer;
import com.yurn.satori.sdk.api.MessageApi;
import com.yurn.satori.sdk.entity.*;
import com.yurn.satori.sdk.entity.events.MessageEvents;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurn
 */
@Slf4j
public class DispatcherMessageCreatedListener {
    private final PropertiesEntity properties;
    private final EventListenerContainer listenerContainer;
    private final String platform;

    public DispatcherMessageCreatedListener(String platform, PropertiesEntity properties, ListenerContainer listenerContainer,
                                            EventListenerContainer eventListenerContainer) {
        this.platform = platform;
        this.properties = properties;
        this.listenerContainer = eventListenerContainer;
        listenerContainer.addOnEventListener(event -> {
            if (event.getType().equals(MessageEvents.MESSAGE_CREATED)) {
                onEvent(event);
            }
        });
    }

    private void onEvent(EventEntity event) {
        MessageApi messageApi = new MessageApi(platform, event.getSelfId(), properties);
        Bot bot = new Bot(messageApi);
        switch (event.getChannel().getType()) {
            case ChannelEntity.TEXT -> {
                GroupMessageCreatedEvent newEvent = new GroupMessageCreatedEvent();
                String msg = initSameEvent(newEvent, event);
                GuildEntity guild = event.getGuild();
                newEvent.setGuild(guild.getId(), guild.getName(), guild.getAvatar());
                newEvent.setSenderCard(event.getMember().getName());
                listenerContainer.runOnMessageCreatedListeners(bot, newEvent, msg);
                listenerContainer.runOnGroupMessageCreatedListeners(bot, newEvent, msg);
            }
            case ChannelEntity.DIRECT -> {
                PrivateMessageCreatedEvent newEvent = new PrivateMessageCreatedEvent();
                String msg = initSameEvent(newEvent, event);
                listenerContainer.runOnMessageCreatedListeners(bot, newEvent, msg);
                listenerContainer.runOnPrivateMessageCreatedListeners(bot, newEvent, msg);
            }
            default -> throw new IllegalStateException("Unexpected value: " + event.getChannel().getType());
        }
    }

    /**
     * @param newEvent 新的事件
     * @param event    原事件
     * @return 过滤后的纯文本
     */
    private String initSameEvent(MessageCreatedEvent newEvent, EventEntity event) {
        newEvent.setTimestamp(event.getTimestamp());
        MessageEntity message = event.getMessage();
        newEvent.setMessage(message.getId(), message.getContent());
        UserEntity user = event.getUser();
        newEvent.setUser(user.getId(), user.getName(), user.getAvatar(), user.getIsBot());
        ChannelEntity channel = event.getChannel();
        newEvent.setChannel(channel.getId(), channel.getType(), channel.getName());
        Element element = Jsoup.parse(message.getContent()).body();
        newEvent.setMsgChain(parse(element.childNodes()));
        return element.text();
    }

    private List<BaseMessageElement> parse(List<Node> nodeList) {
        List<BaseMessageElement> msgChain = new ArrayList<>();
        for (Node node : nodeList) {
            if (node instanceof TextNode textNode) {
                msgChain.add(new TextElement(textNode.text()));
            } else if (node instanceof Element element) {
                switch (element.tagName()) {
                    case "at" -> msgChain.add(new AtElement(element.attr("id"),
                            element.attr("name"),
                            element.attr("type")));
                    case "audio" -> msgChain.add(new AudioElement(element.attr("src"),
                            element.hasAttr("cache"),
                            element.attr("timeout")));
                    case "file" -> msgChain.add(new FileElement(element.attr("src"),
                            element.hasAttr("cache"),
                            element.attr("timeout"),
                            element.attr("chrono-unsafe-filename")));
                    case "img" -> {
                        String widthString = element.attr("width");
                        Long width = widthString.isEmpty() ? null : Long.parseLong(widthString);
                        String heightWidth = element.attr("height");
                        Long height = heightWidth.isEmpty() ? null : Long.parseLong(heightWidth);
                        msgChain.add(new ImgElement(element.attr("src"),
                                element.hasAttr("cache"),
                                element.attr("timeout"),
                                width, height,
                                element.hasAttr("chrono-unsafe-isemoji"),
                                element.attr("chrono-unsafe-filepath")));
                    }
                    case "video" -> msgChain.add(new VideoElement(element.attr("src"),
                            element.hasAttr("cache"),
                            element.attr("timeout")));
                    default -> throw new IllegalStateException("Unsupported tag: " + element.tagName());
                }
            }
        }
        return msgChain;
    }
}
