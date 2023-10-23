/*
Copyright (c) 2023 Yurn
YurnQbotFramework is licensed under Mulan PSL v2.
You can use this software according to the terms and conditions of the Mulan PSL v2.
You may obtain a copy of Mulan PSL v2 at:
         http://license.coscl.org.cn/MulanPSL2
THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
See the Mulan PSL v2 for more details.
 */

package com.yurn.satori.framework.qq.event.message.created;

import com.yurn.satori.framework.qq.entity.event.Channel;
import com.yurn.satori.framework.qq.entity.event.Guild;
import com.yurn.satori.framework.qq.entity.event.Message;
import com.yurn.satori.framework.qq.message.element.BaseMessageElement;
import com.yurn.satori.framework.qq.entity.event.User;
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
public class GroupMessageCreatedEvent extends MessageCreatedEvent {
    /**
     * 群组信息
     */
    protected Guild guild;

    /**
     * 群成员卡片
     */
    protected String senderCard;

    public GroupMessageCreatedEvent(Integer id, Long timestamp, User user, Message message, Channel channel,
                                    List<BaseMessageElement> chain, Guild guild, String senderCard) {
        super(id, timestamp, user, message, channel, chain);
        this.guild = guild;
        this.senderCard = senderCard;
    }

    public void setGuild(String id, String name, String avatar) {
        this.guild = new Guild(id, name, avatar);
    }
}
