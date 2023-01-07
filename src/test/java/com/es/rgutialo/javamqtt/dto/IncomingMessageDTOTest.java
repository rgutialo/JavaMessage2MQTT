package com.es.rgutialo.javamqtt.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IncomingMessageDTOTest {

    public static final String TOPIC = "TOPIC";
    private Object object;


    @BeforeEach
    public void setUp() {
        this.object = new Object();
    }

    @Test
    void builder_shouldCreateIncomingMessage_whenParametersAreOK() {

        final IncomingMessageDTO result = IncomingMessageDTO.builder()
                .topic(TOPIC)
                .payload(object)
                .build();


        assertThat(result.getTopic()).isEqualTo(TOPIC);
        assertThat(result.getPayload()).isEqualTo(object);
    }

    @Test
    void builder_shouldThrowNullPointerException_whenTopicIsNull() {

        assertThrows(NullPointerException.class, () -> {
            IncomingMessageDTO.builder()
                    .topic(null)
                    .payload(object)
                    .build();
        });
    }

    @Test
    void builder_shouldThrowNullPointerException_whenPayloadIsNull() {

        assertThrows(NullPointerException.class, () -> {
            IncomingMessageDTO.builder()
                    .topic(TOPIC)
                    .payload(null)
                    .build();
        });
    }

}