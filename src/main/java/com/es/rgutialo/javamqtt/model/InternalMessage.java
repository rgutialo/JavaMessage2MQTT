package com.es.rgutialo.javamqtt.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InternalMessage {

    private final String topic;
    private final String payload;

}
