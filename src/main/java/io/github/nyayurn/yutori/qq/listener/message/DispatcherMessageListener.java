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

package io.github.nyayurn.yutori.qq.listener.message;

import io.github.nyayurn.yutori.ListenerContainer;
import io.github.nyayurn.yutori.entity.ChannelEntity;
import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.entity.PropertiesEntity;
import io.github.nyayurn.yutori.event.MessageEvents;
import io.github.nyayurn.yutori.qq.entity.event.Bot;
import io.github.nyayurn.yutori.qq.event.message.GroupMessageEvent;
import io.github.nyayurn.yutori.qq.event.message.PrivateMessageEvent;
import io.github.nyayurn.yutori.qq.listener.EventListenerContainer;
import io.github.nyayurn.yutori.qq.message.element.BaseMessageElement;
import io.github.nyayurn.yutori.qq.message.element.basic.AtElement;
import io.github.nyayurn.yutori.qq.message.element.basic.TextElement;
import io.github.nyayurn.yutori.qq.message.element.resource.AudioElement;
import io.github.nyayurn.yutori.qq.message.element.resource.FileElement;
import io.github.nyayurn.yutori.qq.message.element.resource.ImgElement;
import io.github.nyayurn.yutori.qq.message.element.resource.VideoElement;
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
public class DispatcherMessageListener {
    private final PropertiesEntity properties;
    private final EventListenerContainer listenerContainer;
    private final String platform;

    public DispatcherMessageListener(String platform, PropertiesEntity properties, ListenerContainer listenerContainer,
                                     EventListenerContainer eventListenerContainer) {
        this.platform = platform;
        this.properties = properties;
        this.listenerContainer = eventListenerContainer;
        listenerContainer.addOnEventListener(event -> {
            switch (event.getType()) {
                case MessageEvents.MESSAGE_CREATED -> onCreated(event);
                case MessageEvents.MESSAGE_DELETED -> onDeleted(event);
                default -> {}
            }
        });
    }

    private void onCreated(EventEntity event) {
        var bot = new Bot(platform, event.getSelfId(), properties);
        switch (event.getChannel().getType()) {
            case ChannelEntity.TEXT -> {
                Element element = Jsoup.parse(event.getMessage().getContent()).body();
                var newEvent = GroupMessageEvent.parse(event, parse(element.childNodes()));
                var msg = element.text();
                listenerContainer.runOnMessageCreatedListeners(bot, newEvent, msg);
                listenerContainer.runOnGroupMessageCreatedListeners(bot, newEvent, msg);
            }
            case ChannelEntity.DIRECT -> {
                Element element = Jsoup.parse(event.getMessage().getContent()).body();
                var newEvent = PrivateMessageEvent.parse(event, parse(element.childNodes()));
                var msg = element.text();
                listenerContainer.runOnMessageCreatedListeners(bot, newEvent, msg);
                listenerContainer.runOnPrivateMessageCreatedListeners(bot, newEvent, msg);
            }
            default -> throw new IllegalStateException("Unexpected value: " + event.getChannel().getType());
        }
    }

    private void onDeleted(EventEntity event) {
        var bot = new Bot(platform, event.getSelfId(), properties);
        switch (event.getChannel().getType()) {
            case ChannelEntity.TEXT -> {
                Element element = Jsoup.parse(event.getMessage().getContent()).body();
                var newEvent = GroupMessageEvent.parse(event, parse(element.childNodes()));
                var msg = element.text();
                listenerContainer.runOnMessageDeletedListeners(bot, newEvent, msg);
                listenerContainer.runOnGroupMessageDeletedListeners(bot, newEvent, msg);
            }
            case ChannelEntity.DIRECT -> {
                Element element = Jsoup.parse(event.getMessage().getContent()).body();
                var newEvent = PrivateMessageEvent.parse(event, parse(element.childNodes()));
                var msg = element.text();
                listenerContainer.runOnMessageDeletedListeners(bot, newEvent, msg);
                listenerContainer.runOnPrivateMessageDeletedListeners(bot, newEvent, msg);
            }
            default -> throw new IllegalStateException("Unexpected value: " + event.getChannel().getType());
        }
    }

    private List<BaseMessageElement> parse(List<Node> nodeList) {
        var msgChain = new ArrayList<BaseMessageElement>();
        for (var node : nodeList) {
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
                        var widthString = element.attr("width");
                        var width = widthString.isEmpty() ? null : Long.parseLong(widthString);
                        var heightWidth = element.attr("height");
                        var height = heightWidth.isEmpty() ? null : Long.parseLong(heightWidth);
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
