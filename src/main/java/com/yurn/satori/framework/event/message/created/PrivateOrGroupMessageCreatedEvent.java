package com.yurn.satori.framework.event.message.created;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.entity.event.Message;
import com.yurn.satori.framework.event.UserEvent;
import com.yurn.satori.sdk.api.MessageApi;
import com.yurn.satori.sdk.entity.ChannelEntiity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
@Data
@NoArgsConstructor
public class PrivateOrGroupMessageCreatedEvent extends UserEvent {
    /**
     * 消息信息
     */
    protected Message message;

    /**
     * 频道信息
     */
    protected Channel channel;

    public PrivateOrGroupMessageCreatedEvent(Integer id, Long timestamp, Bot bot, User user, Message message, Channel channel) {
        super(id, timestamp, bot, user);
        this.message = message;
        this.channel = channel;
    }

    public void setMessage(String id, String content) {
        this.message = new Message(id, content);
    }

    public void setChannel(String id, Integer type, String name) {
        this.channel = new Channel(id, type, name);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Channel {
        /**
         * 群聊: 群号
         * 私聊: QQ 号
         */
        protected String id;

        /**
         * 群聊: 0
         * 私聊: 3
         */
        protected Integer type;

        /**
         * 群聊: 群名称
         * 私聊: null
         */
        protected String name;

        public void createMessage(String content) {
            switch (type) {
                case ChannelEntiity.TEXT -> MessageApi.createMessage(id, content, "chronocat", bot.getId());
                case ChannelEntiity.DIRECT -> MessageApi.createMessage("private:" + id, content, "chronocat", bot.getId());
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
        }
    }
}