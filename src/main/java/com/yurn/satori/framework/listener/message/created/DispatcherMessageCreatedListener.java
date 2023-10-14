package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.event.message.created.GroupMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateOrGroupMessageCreatedEvent;
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
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurn
 */
@Component
@Slf4j
public class DispatcherMessageCreatedListener {
    private final PrivateOrGroupMessageCreatedListener[] privateOrGroupMessageCreatedListeners;
    private final PrivateMessageCreatedListener[] privateMessageCreatedListeners;
    private final GroupMessageCreatedListener[] groupMessageCreatedListeners;

    @Autowired
    public DispatcherMessageCreatedListener(@Nullable PrivateOrGroupMessageCreatedListener[] privateOrGroupMessageCreatedListeners,
                                            @Nullable PrivateMessageCreatedListener[] privateMessageCreatedListeners,
                                            @Nullable GroupMessageCreatedListener[] groupMessageCreatedListeners) {
        this.privateOrGroupMessageCreatedListeners = privateOrGroupMessageCreatedListeners;
        this.privateMessageCreatedListeners = privateMessageCreatedListeners;
        this.groupMessageCreatedListeners = groupMessageCreatedListeners;
        GlobalEventChannel.INSTANCE.addEvent(event -> {
            if (event.getType().equals(MessageEvents.MESSAGE_CREATED)) {
                onEvent(event);
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void onEvent(EventEntity event) {
        switch (event.getChannel().getType()) {
            case ChannelEntity.TEXT -> {
                GroupMessageCreatedEvent newEvent = new GroupMessageCreatedEvent();
                String msg = initSameEvent(newEvent, event);
                GuildEntity guild = event.getGuild();
                newEvent.setGuild(guild.getId(), guild.getName(), guild.getAvatar());
                newEvent.setSenderCard(event.getMember().getName());
                if (groupMessageCreatedListeners != null) {
                    for (var listener : groupMessageCreatedListeners) {
                        listener.onMessageCreated(newEvent, msg);
                    }
                }
                if (privateOrGroupMessageCreatedListeners != null) {
                    for (var listener : privateOrGroupMessageCreatedListeners) {
                        listener.onMessageCreated(newEvent, msg);
                    }
                }
            }
            case ChannelEntity.DIRECT -> {
                PrivateMessageCreatedEvent newEvent = new PrivateMessageCreatedEvent();
                String msg = initSameEvent(newEvent, event);
                if (privateMessageCreatedListeners != null) {
                    for (var listener : privateMessageCreatedListeners) {
                        listener.onMessageCreated(newEvent, msg);
                    }
                }
                if (privateOrGroupMessageCreatedListeners != null) {
                    for (var listener : privateOrGroupMessageCreatedListeners) {
                        listener.onMessageCreated(newEvent, msg);
                    }
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
    private String initSameEvent(PrivateOrGroupMessageCreatedEvent newEvent, EventEntity event) {
        try {
            newEvent.setTimestamp(event.getTimestamp());
            newEvent.setBot(event.getSelfId());
            MessageEntity message = event.getMessage();
            newEvent.setMessage(message.getId(), message.getContent());
            UserEntity user = event.getUser();
            newEvent.setUser(user.getId(), user.getName(), user.getAvatar(), user.getIsBot());
            ChannelEntity channel = event.getChannel();
            newEvent.setChannel(channel.getId(), channel.getType(), channel.getName());
            @SuppressWarnings("VulnerableCodeUsages") Element rootElement = DocumentHelper.parseText(message.getContent()).getRootElement();
            newEvent.setMsgChain(parse(rootElement.content()));
            return rootElement.getText();
        } catch (DocumentException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    private List<BaseMessageElement> parse(List<?> nodeList) {
        List<BaseMessageElement> msgChain = new ArrayList<>();
        for (Object o : nodeList) {
            Node node = (Node) o;
            if (node instanceof DefaultText textNode) {
                msgChain.add(new TextElement(textNode.getText()));
            } else if (node instanceof DefaultElement element) {
                switch (element.getName()) {
                    case "at" -> msgChain.add(new AtElement(element.attributeValue("id"),
                            element.attributeValue("name"),
                            element.attributeValue("type")));
                    case "audio" -> msgChain.add(new AudioElement(element.attributeValue("src"),
                            Boolean.parseBoolean(element.attributeValue("cache")),
                            element.attributeValue("timeout")));
                    case "file" -> msgChain.add(new FileElement(element.attributeValue("src"),
                            Boolean.parseBoolean(element.attributeValue("cache")),
                            element.attributeValue("timeout"),
                            element.attributeValue("chrono-unsafe-filename")));
                    case "img" -> msgChain.add(new ImgElement(element.attributeValue("src"),
                            Boolean.parseBoolean(element.attributeValue("cache")),
                            element.attributeValue("timeout"),
                            Long.parseLong(element.attributeValue("width")),
                            Long.parseLong(element.attributeValue("height")),
                            Boolean.parseBoolean(element.attributeValue("chrono-unsafe-isemoji")),
                            element.attributeValue("chrono-unsafe-filepath")));
                    case "video" -> msgChain.add(new VideoElement(element.attributeValue("src"),
                            Boolean.parseBoolean(element.attributeValue("cache")),
                            element.attributeValue("timeout")));
                    default -> throw new IllegalStateException("Unsupported tag: " + element.getName());
                }
            }
        }
        return msgChain;
    }
}
