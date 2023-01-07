package com.es.rgutialo.javamqtt.services.impl;

import com.es.rgutialo.javamqtt.model.InternalMessage;
import com.es.rgutialo.javamqtt.model.MqttServer;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MqttSenderServiceImplTest {

    public static final String HOST_NAME = "LOCALHOST";
    public static final String PORT = "1883";
    public static final int QOS = 2;
    public static final String USER = "USER";
    public static final String PASSWORD = "UEFTU1dPUkQ=";
    public static final String NOT_DEFINED = "NOT_DEFINED";
    public static final String PAYLOAD = "PAYLOAD";
    public static final String TOPIC = "TOPIC";
    @InjectMocks
    private MqttSenderServiceImpl testObj;
    @Mock
    private MqttServer mqttServerMock;
    @Mock
    private MqttClient mqttClientMock;
    @Mock
    private InternalMessage internalMessageMock;

    @Test
    public void getServerInfo_shouldReturnServerInfo_whenInfoIsSetUp() {
        when(mqttServerMock.getHostName()).thenReturn(HOST_NAME);
        when(mqttServerMock.getPort()).thenReturn(PORT);
        when(mqttServerMock.getUser()).thenReturn(USER);
        when(mqttServerMock.getPassword()).thenReturn(PASSWORD);

        final var result = testObj.getServerInfo();

        assertThat(result.getHostName()).isEqualTo(HOST_NAME);
        assertThat(result.getPort()).isEqualTo(PORT);
        assertThat(result.getUser()).isEqualTo(USER);
        assertThat(result.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    public void getServerInfo_shouldReturnNotDefinedInfoInfo_whenMqttServerInstanceIsNull() {
        testObj = new MqttSenderServiceImpl(null, null);

        final var result = testObj.getServerInfo();

        assertThat(result.getHostName()).isEqualTo(NOT_DEFINED);
        assertThat(result.getPort()).isEqualTo(NOT_DEFINED);
        assertThat(result.getUser()).isEqualTo(NOT_DEFINED);
        assertThat(result.getPassword()).isEqualTo(NOT_DEFINED);
    }

    @Test
    public void sendMessage_shouldReturnNotDefinedInfoInfo_whenMqttServerInstanceIsNull() throws MqttException {
        when(mqttClientMock.isConnected()).thenReturn(Boolean.TRUE);
        when(internalMessageMock.getTopic()).thenReturn(TOPIC);
        when(internalMessageMock.getPayload()).thenReturn(PAYLOAD);
        when(mqttServerMock.getQos()).thenReturn(QOS);

        testObj.sendMessage(internalMessageMock);

        verify(mqttClientMock).publish(eq(TOPIC), any(MqttMessage.class));

    }

}

