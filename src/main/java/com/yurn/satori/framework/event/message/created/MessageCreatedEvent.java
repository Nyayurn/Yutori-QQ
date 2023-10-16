package com.yurn.satori.framework.event.message.created;

import com.yurn.satori.framework.entity.event.Channel;
import com.yurn.satori.framework.entity.event.Message;
import com.yurn.satori.framework.entity.event.User;
import com.yurn.satori.framework.event.UserEvent;
import com.yurn.satori.framework.message.element.BaseMessageElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MessageCreatedEvent extends UserEvent {
    /**
     * 消息信息
     */
    protected Message message;

    /**
     * 频道信息
     */
    protected Channel channel;

    /**
     * 消息链
     */
    protected List<BaseMessageElement> msgChain;

    public MessageCreatedEvent(Integer id, Long timestamp, User user, Message message, Channel channel, List<BaseMessageElement> chain) {
        super(id, timestamp, user);
        this.message = message;
        this.channel = channel;
        this.msgChain = chain;
    }

    public void setMessage(String id, String content) {
        this.message = new Message(id, content);
    }

    public void setChannel(String id, Integer type, String name) {
        this.channel = new Channel(id, type, name);
    }

}