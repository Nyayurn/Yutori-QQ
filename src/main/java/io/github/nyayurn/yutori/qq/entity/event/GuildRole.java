/*
Copyright (c) 2023 Yurn
yutori is licensed under Mulan PSL v2.
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
 * 群组角色
 *
 * @author Yurn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuildRole {
    /**
     * 角色 ID
     * 不可为 null
     */
    private String id;

    /**
     * 角色名称
     * 可为 null
     */
    private String name;

    public static GuildRole parse(EventEntity event) {
        return new GuildRole(event.getRole().getId(), event.getRole().getName());
    }
}
