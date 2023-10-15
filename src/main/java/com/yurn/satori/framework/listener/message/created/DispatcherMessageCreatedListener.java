package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.event.message.created.GroupMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.MessageCreatedEvent;
import com.yurn.satori.framework.listener.ListenerContainer;
import com.yurn.satori.framework.message.element.BaseMessageElement;
import com.yurn.satori.framework.message.element.basic.AtElement;
import com.yurn.satori.framework.message.element.basic.TextElement;
import com.yurn.satori.framework.message.element.resource.AudioElement;
import com.yurn.satori.framework.message.element.resource.FileElement;
import com.yurn.satori.framework.message.element.resource.ImgElement;
import com.yurn.satori.framework.message.element.resource.VideoElement;
import com.yurn.satori.sdk.GlobalEventChannel;
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
    public DispatcherMessageCreatedListener() {
        GlobalEventChannel.getINSTANCE().addEvent(event -> {
            if (event.getType().equals(MessageEvents.MESSAGE_CREATED)) {
                onEvent(event);
            }
        });
    }

    private void onEvent(EventEntity event) {
        switch (event.getChannel().getType()) {
            case ChannelEntity.TEXT -> {
                GroupMessageCreatedEvent newEvent = new GroupMessageCreatedEvent();
                String msg = initSameEvent(newEvent, event);
                GuildEntity guild = event.getGuild();
                newEvent.setGuild(guild.getId(), guild.getName(), guild.getAvatar());
                newEvent.setSenderCard(event.getMember().getName());
                for (var listener : ListenerContainer.getINSTANCE().GROUP_MESSAGE_CREATED_LISTENER_LIST) {
                    listener.onMessageCreated(newEvent, msg);
                }
                for (var listener : ListenerContainer.getINSTANCE().MESSAGE_CREATED_LISTENER_LIST) {
                    listener.onMessageCreated(newEvent, msg);
                }
            }
            case ChannelEntity.DIRECT -> {
                PrivateMessageCreatedEvent newEvent = new PrivateMessageCreatedEvent();
                String msg = initSameEvent(newEvent, event);
                for (var listener : ListenerContainer.getINSTANCE().MESSAGE_CREATED_LISTENER_LIST) {
                    listener.onMessageCreated(newEvent, msg);
                }
                for (var listener : ListenerContainer.getINSTANCE().PRIVATE_MESSAGE_CREATED_LISTENER_LIST) {
                    listener.onMessageCreated(newEvent, msg);
                }
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
        newEvent.setBot(event.getSelfId());
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
                    case "img" -> msgChain.add(new ImgElement(element.attr("src"),
                            element.hasAttr("cache"),
                            element.attr("timeout"),
                            Long.parseLong(element.attr("width")),
                            Long.parseLong(element.attr("height")),
                            element.hasAttr("chrono-unsafe-isemoji"),
                            element.attr("chrono-unsafe-filepath")));
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
