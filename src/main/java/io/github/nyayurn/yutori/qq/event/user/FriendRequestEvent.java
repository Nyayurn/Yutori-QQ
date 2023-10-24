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

package io.github.nyayurn.yutori.qq.event.user;

import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.qq.entity.event.User;
import io.github.nyayurn.yutori.qq.event.UserEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FriendRequestEvent extends UserEvent {
    public FriendRequestEvent(Integer id, Long timestamp, User user) {
        super(id, timestamp, user);
    }

    public static FriendRequestEvent parse(EventEntity event) {
        return new FriendRequestEvent(event.getId(), event.getTimestamp(), User.parse(event));
    }
}