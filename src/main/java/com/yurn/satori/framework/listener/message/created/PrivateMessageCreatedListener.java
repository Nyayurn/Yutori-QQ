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

package com.yurn.satori.framework.listener.message.created;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.message.created.PrivateMessageCreatedEvent;

/**
 * @author Yurn
 */
public interface PrivateMessageCreatedListener {
    /**
     * 触发事件
     *
     * @param bot   机器人信息
     * @param event 事件信息
     * @param msg   过滤后的普通文本信息
     */
    void onMessageCreated(Bot bot, PrivateMessageCreatedEvent event, String msg);
}
