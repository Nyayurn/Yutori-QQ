package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.event.message.created.GroupMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateMessageCreatedEvent;
import com.yurn.satori.framework.event.message.created.PrivateOrGroupMessageCreatedEvent;
import com.yurn.satori.sdk.GlobalEventChannel;
import com.yurn.satori.sdk.entity.*;
import com.yurn.satori.sdk.entity.events.MessageEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Yurn
 */
@Component
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
                initSameEvent(newEvent, event);
                GuildEntity guild = event.getGuild();
                newEvent.setGuild(guild.getId(), guild.getName(), guild.getAvatar());
                newEvent.setSenderCard(event.getMember().getName());
                String msg = newEvent.getMessage().getContent();
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
                initSameEvent(newEvent, event);
                String msg = newEvent.getMessage().getContent();
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

    private void initSameEvent(PrivateOrGroupMessageCreatedEvent newEvent, EventEntity event) {
        newEvent.setTimestamp(event.getTimestamp());
        newEvent.setBot(event.getSelfId());
        MessageEntity message = event.getMessage();
        newEvent.setMessage(message.getId(), message.getContent());
        UserEntity user = event.getUser();
        newEvent.setUser(user.getId(), user.getName(), user.getAvatar(), user.getIsBot());
        ChannelEntity channel = event.getChannel();
        newEvent.setChannel(channel.getId(), channel.getType(), channel.getName());
    }
}
