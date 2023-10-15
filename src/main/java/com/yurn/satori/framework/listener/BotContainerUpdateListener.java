package com.yurn.satori.framework.listener;

import com.yurn.satori.framework.BotContainer;
import com.yurn.satori.sdk.GlobalEventChannel;
import com.yurn.satori.sdk.entity.LoginEntity;

import java.util.Arrays;

/**
 * @author Yurn
 */
public class BotContainerUpdateListener {
    public BotContainerUpdateListener() {
        GlobalEventChannel.INSTANCE.addConnect(ready -> BotContainer.setBots(
                Arrays.stream(ready.getLogins()).filter(login -> "chronocat".equals(login.getPlatform())).toArray(LoginEntity[]::new)));
        GlobalEventChannel.INSTANCE.addDisconnect(s -> BotContainer.setBots(null));
    }
}
