package com.yurn.satori.framework.entity.event;

import com.yurn.satori.sdk.api.MessageApi;
import com.yurn.satori.sdk.entity.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yurn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bot {
    /**
     * QQ 号
     */
    private String id;

    /**
     * 获取昵称
     */
    public String getName() {
        return null;
    }

    /**
     * 获取头像
     */
    public String getAvatar() {
        return null;
    }

    public List<MessageEntity> createGroupMessage(String id, String content) {
        return MessageApi.createMessage(id, content, "chronocat", id);
    }

    public List<MessageEntity> createPrivateMessage(String id, String content) {
        return MessageApi.createMessage("private:" + id, content, "chronocat", id);
    }
}