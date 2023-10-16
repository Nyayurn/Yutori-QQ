package com.yurn.satori.framework.event;

import com.yurn.satori.framework.entity.event.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserEvent extends Event {
    /**
     * 用户信息
     */
    private User user;

    public UserEvent(Integer id, Long timestamp, User user) {
        super(id, timestamp);
        this.user = user;
    }

    public void setUser(String name, String id, String avatar, Boolean isBot) {
        this.user = new User(name, id, avatar, isBot);
    }

}
