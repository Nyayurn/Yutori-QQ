package com.yurn.satori.framework.event;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.sdk.api.MessageApi;
import com.yurn.satori.sdk.entity.MessageEntity;
import lombok.AllArgsConstructor;
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
public class UserEvent extends Event {
    /**
     * 用户信息
     */
    private User user;

    public UserEvent(Integer id, Long timestamp, Bot bot, User user) {
        super(id, timestamp, bot);
        this.user = user;
    }

    public void setUser(String name, String id, String avatar, Boolean isBot) {
        this.user = new User(name, id, avatar, isBot);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {
        /**
         * QQ 号
         */
        private String id;

        /**
         * 昵称
         */
        private String name;

        /**
         * 头像
         */
        private String avatar;

        /**
         * 是否为机器人(null 表示未知)
         */
        private Boolean isBot;

        public List<MessageEntity> createMessage(String content) {
            return MessageApi.createMessage("private:" + id, content, "chronocat", bot.getId());
        }
    }
}
