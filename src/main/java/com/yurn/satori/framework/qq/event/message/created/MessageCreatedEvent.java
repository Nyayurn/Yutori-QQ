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
import com.yurn.satori.framework.qq.entity.event.Message;
import com.yurn.satori.framework.qq.message.element.BaseMessageElement;
import com.yurn.satori.framework.qq.entity.event.User;
import com.yurn.satori.framework.qq.event.UserEvent;
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
public class MessageCreatedEvent extends UserEvent {
    /**
     * 消息信息
     */
    protected Message message;

    /**
     * 频道信息
     */
    protected Channel channel;

    /**
     * 消息链
     */
    protected List<BaseMessageElement> msgChain;

    public MessageCreatedEvent(Integer id, Long timestamp, User user, Message message, Channel channel, List<BaseMessageElement> chain) {
        super(id, timestamp, user);
        this.message = message;
        this.channel = channel;
        this.msgChain = chain;
    }

    public void setMessage(String id, String content) {
        this.message = new Message(id, content);
    }

    public void setChannel(String id, Integer type, String name) {
        this.channel = new Channel(id, type, name);
    }

}