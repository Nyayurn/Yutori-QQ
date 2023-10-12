package com.yurn.satori.framework.event;

import com.yurn.satori.framework.entity.event.Bot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    /**
     * 事件 ID
     */
    protected Integer id;

    /**
     * 事件的时间戳
     */
    protected Long timestamp;

    /**
     * 机器人信息
     */
    protected Bot bot;

    public void setBot(String id) {
        this.bot = new Bot(id);
    }
}
