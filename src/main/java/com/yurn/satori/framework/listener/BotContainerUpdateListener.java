package com.yurn.satori.framework.listener;

import com.yurn.satori.framework.BotContainer;
import com.yurn.satori.sdk.GlobalEventChannel;
import com.yurn.satori.sdk.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Yurn
 */
@Component
public class BotContainerUpdateListener {
    @Autowired
    public BotContainerUpdateListener(BotContainer botContainer) {
        GlobalEventChannel.INSTANCE.addConnect(ready -> botContainer.setBots(
                Arrays.stream(ready.getLogins()).filter(login -> "chronocat".equals(login.getPlatform())).toArray(LoginEntity[]::new)));
        GlobalEventChannel.INSTANCE.addDisconnect(s -> botContainer.setBots(null));
    }
}
