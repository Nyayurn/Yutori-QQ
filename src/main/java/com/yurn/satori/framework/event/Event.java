package com.yurn.satori.framework.event;

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
}
