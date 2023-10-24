package io.github.nyayurn.yutori.qq.event.guild;

import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.qq.entity.event.Guild;
import io.github.nyayurn.yutori.qq.entity.event.GuildMember;
import io.github.nyayurn.yutori.qq.entity.event.User;
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
public class GuildMemberEvent extends Event {
    /**
     * 群组信息
     */
    protected Guild guild;

    /**
     * 群成员信息
     */
    protected GuildMember member;

    public GuildMemberEvent(Integer id, Long timestamp, Guild guild, GuildMember member) {
        super(id, timestamp);
        this.guild = guild;
        this.member = member;
    }

    public static GuildMemberEvent parse(EventEntity event) {
        return new GuildMemberEvent(event.getId(), event.getTimestamp(), Guild.parse(event), GuildMember.parse(event, User.parse(event)));
    }
}