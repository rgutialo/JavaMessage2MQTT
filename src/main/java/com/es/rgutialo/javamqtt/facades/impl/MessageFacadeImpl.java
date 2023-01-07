package com.es.rgutialo.javamqtt.facades.impl;

import com.es.rgutialo.javamqtt.dto.IncomingMessageDTO;
import com.es.rgutialo.javamqtt.dto.ServerInfoDTO;
import com.es.rgutialo.javamqtt.facades.MessageFacade;
import com.es.rgutialo.javamqtt.model.InternalMessage;
import com.es.rgutialo.javamqtt.model.ServerInfoMessage;
import com.es.rgutialo.javamqtt.services.MqttSenderService;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class MessageFacadeImpl implements MessageFacade {

    @Resource
    private final MqttSenderService mqttSenderService;

    private final Gson gson;

    public MessageFacadeImpl(MqttSenderService mqttSenderService) {
        this.mqttSenderService = mqttSenderService;
        this.gson = new Gson();
    }

    @Override
    public void transformMessage(IncomingMessageDTO incomingMessage) {

        InternalMessage internalMqttMessage = InternalMessage.builder()
                .topic(incomingMessage.getTopic())
                .payload(gson.toJson(incomingMessage.getPayload(), Object.class))
                .build();

        mqttSenderService.sendMessage(internalMqttMessage);

    }

    @Override
    public ServerInfoDTO getServerInfo() {
        ServerInfoMessage serverInfo = mqttSenderService.getServerInfo();

        return ServerInfoDTO.builder()
                .hostName(serverInfo.getHostName())
                .port(serverInfo.getPort())
                .user(serverInfo.getUser())
                .password(serverInfo.getPassword())
                .build();
    }
}
