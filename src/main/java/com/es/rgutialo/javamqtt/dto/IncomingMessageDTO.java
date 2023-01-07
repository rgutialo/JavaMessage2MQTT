package com.es.rgutialo.javamqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Manages the model related to external incomming messages to the application
 */

@Builder
@Getter
public class IncomingMessageDTO {

    @NonNull
    private final String topic;
    @NonNull
    private final Object payload;

}
