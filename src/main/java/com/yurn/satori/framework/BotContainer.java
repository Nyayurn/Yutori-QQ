package com.yurn.satori.framework;

import com.yurn.satori.sdk.entity.LoginEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Yurn
 */
@Component
@Data
public class BotContainer {
    private LoginEntity[] bots;
}
