package com.yurn.satori.framework.event.user;

import com.yurn.satori.framework.entity.event.User;
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
    public FriendRequestEvent(Integer id, Long timestamp, User user) {
        super(id, timestamp, user);
    }
}