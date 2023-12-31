/*
Copyright (c) 2023 Yurn
yutori-qq is licensed under Mulan PSL v2.
You can use this software according to the terms and conditions of the Mulan PSL v2.
You may obtain a copy of Mulan PSL v2 at:
         http://license.coscl.org.cn/MulanPSL2
THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
See the Mulan PSL v2 for more details.
 */

package io.github.nyayurn.yutori.qq.entity.event;

import io.github.nyayurn.yutori.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
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

    public static User parse(EventEntity event) {
        return new User(event.getUser().getId(), event.getUser().getName(), event.getUser().getAvatar(), event.getUser().getIsBot());
    }
}
