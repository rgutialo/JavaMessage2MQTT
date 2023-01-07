package com.es.rgutialo.javamqtt.services;

import com.es.rgutialo.javamqtt.model.InternalMessage;
import com.es.rgutialo.javamqtt.model.ServerInfoMessage;

public interface MqttSenderService {

    ServerInfoMessage getServerInfo();

    void sendMessage(final InternalMessage internalMessage);
}
