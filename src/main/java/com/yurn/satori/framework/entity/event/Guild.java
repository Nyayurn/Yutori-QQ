package com.yurn.satori.framework.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yurn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guild {
    /**
     * 群号
     */
    private String id;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群图标
     */
    private String avatar;
}