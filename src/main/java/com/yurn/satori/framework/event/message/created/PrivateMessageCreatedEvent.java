package com.yurn.satori.framework.event.message.created;

import com.yurn.satori.framework.entity.event.Channel;
import com.yurn.satori.framework.entity.event.Message;
import com.yurn.satori.framework.entity.event.User;
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
public class PrivateMessageCreatedEvent extends MessageCreatedEvent {
    public PrivateMessageCreatedEvent(Integer id, Long timestamp, User user, Message message, Channel channel,
                                      List<BaseMessageElement> chain) {
        super(id, timestamp, user, message, channel, chain);
    }
}