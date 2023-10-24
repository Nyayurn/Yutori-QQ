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

package io.github.nyayurn.yutori.qq.event.message;

import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.qq.entity.event.Channel;
import io.github.nyayurn.yutori.qq.entity.event.Guild;
import io.github.nyayurn.yutori.qq.entity.event.Message;
import io.github.nyayurn.yutori.qq.entity.event.User;
import io.github.nyayurn.yutori.qq.message.element.BaseMessageElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GroupMessageEvent extends MessageEvent {
    /**
     * 群组信息
     */
    protected Guild guild;

    /**
     * 群成员卡片
     */
    protected String senderCard;

    public GroupMessageEvent(Integer id, Long timestamp, User user, Message message, Channel channel,
                             List<BaseMessageElement> chain, Guild guild, String senderCard) {
        super(id, timestamp, user, message, channel, chain);
        this.guild = guild;
        this.senderCard = senderCard;
    }

    public static GroupMessageEvent parse(EventEntity event, List<BaseMessageElement> chain) {
        return new GroupMessageEvent(event.getId(), event.getTimestamp(), User.parse(event),
                Message.parse(event), Channel.parse(event), chain, Guild.parse(event), event.getMember().getName());
    }
}
