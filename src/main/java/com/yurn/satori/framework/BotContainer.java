package com.yurn.satori.framework;

import com.yurn.satori.sdk.entity.LoginEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yurn
 */
public class BotContainer {
    @Getter
    @Setter
    private static LoginEntity[] bots;
}
