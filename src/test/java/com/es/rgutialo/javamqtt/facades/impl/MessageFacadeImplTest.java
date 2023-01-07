package com.es.rgutialo.javamqtt.facades.impl;

import com.es.rgutialo.javamqtt.dto.IncomingMessageDTO;
import com.es.rgutialo.javamqtt.model.InternalMessage;
import com.es.rgutialo.javamqtt.services.MqttSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageFacadeImplTest {

    public static final String TOPIC = "TOPIC";
    public static final String PAYLOAD = "PAYLOAD";
    @Spy
    @InjectMocks
    private MessageFacadeImpl testObj;
    @Mock
    private MqttSenderService mqttSenderServiceMock;
    @Mock
    private IncomingMessageDTO incomingMessageDTOMock;

    @Captor
    private ArgumentCaptor<InternalMessage> internalMessageArgumentCaptor;


    @BeforeEach
    void setUp() {
        testObj = Mockito.spy(new MessageFacadeImpl(mqttSenderServiceMock));
    }

    @Test
    public void transformMessage() {
        when(incomingMessageDTOMock.getTopic()).thenReturn(TOPIC);
        when(incomingMessageDTOMock.getPayload()).thenReturn(PAYLOAD);

        testObj.transformMessage(incomingMessageDTOMock);

        Mockito.verify(mqttSenderServiceMock).sendMessage(internalMessageArgumentCaptor.capture());
        final var result = internalMessageArgumentCaptor.getValue();
        assertThat(result.getTopic()).isEqualTo(TOPIC);
        assertThat(result.getPayload()).isEqualTo("\"" + PAYLOAD + "\"");
    }

}