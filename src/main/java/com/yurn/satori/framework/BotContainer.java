package com.yurn.satori.framework;

import com.yurn.satori.sdk.entity.LoginEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yurn
 */
@Data
public final class BotContainer {
    @Getter
    private static BotContainer INSTANCE = new BotContainer();

    private LoginEntity[] bots;

    private BotContainer() {}
}

