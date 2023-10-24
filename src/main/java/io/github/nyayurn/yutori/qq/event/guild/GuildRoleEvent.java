package io.github.nyayurn.yutori.qq.event.guild;

import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.qq.entity.event.Guild;
import io.github.nyayurn.yutori.qq.entity.event.GuildRole;
import io.github.nyayurn.yutori.qq.event.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GuildRoleEvent extends Event {
    /**
     * 群组信息
     */
    protected Guild guild;

    /**
     * 角色信息
     */
    protected GuildRole role;

    public GuildRoleEvent(Integer id, Long timestamp, Guild guild, GuildRole role) {
        super(id, timestamp);
        this.guild = guild;
        this.role = role;
    }

    public static GuildRoleEvent parse(EventEntity event) {
        return new GuildRoleEvent(event.getId(), event.getTimestamp(), Guild.parse(event), GuildRole.parse(event));
    }
}