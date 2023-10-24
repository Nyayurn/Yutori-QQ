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

import io.github.nyayurn.yutori.api.*;
import io.github.nyayurn.yutori.entity.PropertiesEntity;
import lombok.Data;

/**
 * @author Yurn
 */
@Data
public class Bot {
    /**
     * QQ 号
     */
    private String id;
    private ChannelApi channelApi;
    private GuildApi guildApi;
    private GuildMemberApi guildMemberApi;
    private GuildRoleApi guildRoleApi;
    private LoginApi loginApi;
    private MessageApi messageApi;
    private UserApi userApi;

    public Bot(String platform, String selfId, PropertiesEntity properties) {
        this.id = selfId;
        this.channelApi = new ChannelApi(platform, selfId, properties);
        this.guildApi = new GuildApi(platform, selfId, properties);
        this.guildMemberApi = new GuildMemberApi(platform, selfId, properties);
        this.guildRoleApi = new GuildRoleApi(platform, selfId, properties);
        this.loginApi = new LoginApi(properties);
        this.messageApi = new MessageApi(platform, selfId, properties);
        this.userApi = new UserApi(platform, selfId, properties);
    }
}