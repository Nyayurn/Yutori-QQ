package com.yurn.satori.framework.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
