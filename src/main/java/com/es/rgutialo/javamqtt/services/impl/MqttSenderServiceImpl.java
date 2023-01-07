package com.es.rgutialo.javamqtt.services.impl;

import com.es.rgutialo.javamqtt.model.InternalMessage;
import com.es.rgutialo.javamqtt.model.MqttServer;
import com.es.rgutialo.javamqtt.model.ServerInfoMessage;
import com.es.rgutialo.javamqtt.services.MqttSenderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class MqttSenderServiceImpl implements MqttSenderService {
    public static final String NOT_DEFINED = "NOT_DEFINED";
    @Resource
    private final MqttServer mqttServer;
    @Resource
    private final MqttClient mqttClient;

    public MqttSenderServiceImpl(MqttServer mqttServer, MqttClient mqttClient) {
        this.mqttServer = mqttServer;
        this.mqttClient = mqttClient;
    }

    @Override
    public ServerInfoMessage getServerInfo() {

        if (Objects.isNull(mqttServer)) {
            return ServerInfoMessage.builder()
                    .hostName(NOT_DEFINED)
                    .port(NOT_DEFINED)
                    .user(NOT_DEFINED)
                    .password(NOT_DEFINED)
                    .build();
        }

        return ServerInfoMessage.builder()
                .hostName(mqttServer.getHostName())
                .port(mqttServer.getPort())
                .user(mqttServer.getUser())
                .password(mqttServer.getPassword())
                .build();

    }

    @Override
    public void sendMessage(final InternalMessage internalMessage) {
        if (mqttClient.isConnected()) {
            MqttMessage mqttMessage = new MqttMessage(internalMessage.getPayload().getBytes());
            mqttMessage.setQos(mqttServer.getQos());
            try {
                mqttClient.publish(internalMessage.getTopic(), mqttMessage);
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error(ex.getMessage());
            }
        }
    }
}
