package com.yurn.satori.framework.event.message.created;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.entity.event.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PrivateMessageCreatedEvent extends PrivateOrGroupMessageCreatedEvent {
    public PrivateMessageCreatedEvent(Integer id, Long timestamp, Bot bot, User user, Message message, Channel channel) {
        super(id, timestamp, bot, user, message, channel);
    }
}