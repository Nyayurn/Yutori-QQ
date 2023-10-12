package com.yurn.satori.framework.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * 消息 ID
     */
    private String id;

    /**
     * 消息内容
     */
    private String content;
}
