package com.yurn.satori.framework.event.message.created;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.entity.event.Guild;
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
public class GroupMessageCreatedEvent extends PrivateOrGroupMessageCreatedEvent {
    /**
     * 群组信息
     */
    protected Guild guild;

    /**
     * 群成员信息
     */
    protected String senderCard;

    public GroupMessageCreatedEvent(Integer id, Long timestamp, Bot bot, User user, Message message, Channel channel, Guild guild, String senderCard) {
        super(id, timestamp, bot, user, message, channel);
        this.guild = guild;
        this.senderCard = senderCard;
    }

    public void setGuild(String id, String name, String avatar) {
        this.guild = new Guild(id, name, avatar);
    }
}
