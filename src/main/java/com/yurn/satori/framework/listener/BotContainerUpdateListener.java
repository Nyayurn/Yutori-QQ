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
        GlobalEventChannel.getINSTANCE().addConnect(ready -> BotContainer.getINSTANCE().setBots(
                Arrays.stream(ready.getLogins()).filter(login -> "chronocat".equals(login.getPlatform())).toArray(LoginEntity[]::new)));
        GlobalEventChannel.getINSTANCE().addDisconnect(s -> BotContainer.getINSTANCE().setBots(null));
    }
}
