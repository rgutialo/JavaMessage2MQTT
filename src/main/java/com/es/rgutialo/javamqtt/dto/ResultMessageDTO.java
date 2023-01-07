package com.es.rgutialo.javamqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * DTO which manages result messages after being processed
 */
@Builder
@Getter
public class ResultMessageDTO {

    @NonNull
    private final String code;
    private final String message;
    private final String errorMessage;
}
