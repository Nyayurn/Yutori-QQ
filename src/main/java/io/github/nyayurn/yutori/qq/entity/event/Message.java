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
public class Message {
    /**
     * 消息 ID
     */
    private String id;

    /**
     * 消息内容
     */
    private String content;

    public static Message parse(EventEntity event) {
        return new Message(event.getMessage().getId(), event.getMessage().getContent());
    }
}
