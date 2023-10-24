package io.github.nyayurn.yutori.qq.event.guild;

import io.github.nyayurn.yutori.entity.EventEntity;
import io.github.nyayurn.yutori.qq.entity.event.Guild;
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
public class GuildEvent extends Event {
    /**
     * 群组信息
     */
    protected Guild guild;

    public GuildEvent(Integer id, Long timestamp, Guild guild) {
        super(id, timestamp);
        this.guild = guild;
    }

    public static GuildEvent parse(EventEntity event) {
        return new GuildEvent(event.getId(), event.getTimestamp(), Guild.parse(event));
    }
}
