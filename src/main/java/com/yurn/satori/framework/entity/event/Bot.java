package com.yurn.satori.framework.entity.event;

import com.yurn.satori.sdk.api.MessageApi;
import com.yurn.satori.sdk.entity.MessageEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Yurn
 */
@Data
public class Bot {
    /**
     * QQ 号
     */
    private String id;

    private MessageApi messageApi;

    public Bot(MessageApi messageApi) {
        this.messageApi = messageApi;
        this.id = messageApi.getSelfId();
    }

    public List<MessageEntity> createMessage(Channel channel, String content) {
        if (channel.getType().equals(0)) {
            return createGroupMessage(channel.getId(), content);
        } else {
            return createPrivateMessage(channel.getId(), content);
        }
    }

    public List<MessageEntity> createMessage(Guild guild, String content) {
        return createGroupMessage(guild.getId(), content);
    }

    public List<MessageEntity> createMessage(User user, String content) {
        return createPrivateMessage(user.getId(), content);
    }

    public List<MessageEntity> createMessage(String id, String content) {
        return messageApi.createMessage(id, content);
    }

    public List<MessageEntity> createGroupMessage(String id, String content) {
        return messageApi.createMessage(id, content);
    }

    public List<MessageEntity> createPrivateMessage(String id, String content) {
        return messageApi.createMessage("private:" + id, content);
    }
}