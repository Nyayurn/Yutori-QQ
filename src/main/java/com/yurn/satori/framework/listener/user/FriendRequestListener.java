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

package com.yurn.satori.framework.listener.user;

import com.yurn.satori.framework.entity.event.Bot;
import com.yurn.satori.framework.event.user.FriendRequestEvent;

/**
 * @author Yurn
 */
public interface FriendRequestListener {
    /**
     * 触发事件
     *
     * @param bot   机器人信息
     * @param event 事件信息
     */
    void onFriendRequest(Bot bot, FriendRequestEvent event);
}
