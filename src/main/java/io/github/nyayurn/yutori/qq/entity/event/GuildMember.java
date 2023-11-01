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

import java.util.Optional;

/**
 * 群组成员
 *
 * @author Yurn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuildMember {
    /**
     * 用户对象
     * 可为 null
     */
    private User user;

    /**
     * 用户在群组中的名称
     * 可为 null
     */
    private String name;

    /**
     * 加入时间
     * 可为 null
     */
    private Long joinedAt;

    public static GuildMember parse(EventEntity event, User user) {
        return Optional.ofNullable(event.getMember())
                .map(guildMemberEntity -> new GuildMember(user, guildMemberEntity.getName(), event.getMember().getJoinedAt()))
                .orElseGet(() -> new GuildMember(user, event.getUser().getName(), null));
    }
}
