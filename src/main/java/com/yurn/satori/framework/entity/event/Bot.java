package com.yurn.satori.framework.entity.event;

import com.yurn.satori.sdk.api.MessageApi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public void createGroupMessage(String id, String content) {
        MessageApi.createMessage(id, content, "chronocat", id);
    }

    public void createPrivateMessage(String id, String content) {
        MessageApi.createMessage("private:" + id, content, "chronocat", id);
    }
}