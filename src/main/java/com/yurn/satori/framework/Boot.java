package com.yurn.satori.framework;

import com.yurn.satori.framework.listener.BotContainerUpdateListener;
import com.yurn.satori.framework.listener.message.created.DispatcherMessageCreatedListener;
import com.yurn.satori.framework.listener.user.DispatcherUserListener;
import com.yurn.satori.sdk.entity.PropertiesEntity;
import lombok.Getter;

/**
 * @author Yurn
 */
@Getter
public class Boot implements Runnable {
    private final com.yurn.satori.sdk.Boot boot;

    public Boot(String address, String token) {
        boot = new com.yurn.satori.sdk.Boot(new PropertiesEntity(address, token));
        new BotContainerUpdateListener();
        new DispatcherUserListener();
        new DispatcherMessageCreatedListener();
    }

    @Override
    public void run() {
        boot.run();
    }
}
