package com.yurn.satori.framework;

import com.yurn.satori.framework.listener.EventListenerContainer;
import com.yurn.satori.framework.listener.message.created.DispatcherMessageCreatedListener;
import com.yurn.satori.framework.listener.user.DispatcherUserListener;
import com.yurn.satori.sdk.ListenerContainer;
import com.yurn.satori.sdk.MyWebSocketClient;
import com.yurn.satori.sdk.entity.PropertiesEntity;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.concurrent.TimeUnit;

/**
 * @author Yurn
 */
@Getter
public class Boot implements Runnable {
    private final ListenerContainer listenerContainer = new ListenerContainer();
    private final PropertiesEntity properties;

    public Boot(String address, String token, EventListenerContainer eventListenerContainer) {
        this("chronocat", address, token, eventListenerContainer);
    }

    public Boot(String platform, String address, String token, EventListenerContainer eventListenerContainer) {
        properties = new PropertiesEntity(address, token);
        new DispatcherUserListener(platform, properties, listenerContainer, eventListenerContainer);
        new DispatcherMessageCreatedListener(platform, properties, listenerContainer, eventListenerContainer);
    }

    @Override
    public void run() {
        // 构造一个 HTTP 客户端用于进行 WebSocket 连接
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .build();
        // 构造一个 Request 请求
        Request request = new Request.Builder().get().url("ws://" + properties.getAddress() + "/v1/events").build();
        // 新建一个 WebSocket 连接
        client.newWebSocket(request, new MyWebSocketClient(properties.getToken(), listenerContainer));
    }
}
