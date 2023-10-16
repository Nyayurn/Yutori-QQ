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
public class Channel {
    /**
     * 群聊: 群号
     * 私聊: QQ 号
     */
    private String id;

    /**
     * 群聊: 0
     * 私聊: 3
     */
    private Integer type;

    /**
     * 群聊: 群名称
     * 私聊: null
     */
    private String name;
}
