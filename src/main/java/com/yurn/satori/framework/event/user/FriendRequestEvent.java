package com.yurn.satori.framework.event.user;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.UserEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FriendRequestEvent extends UserEvent {
    public FriendRequestEvent(Integer id, Long timestamp, Bot bot, User user) {
        super(id, timestamp, bot, user);
    }
}