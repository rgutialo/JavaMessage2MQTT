package com.es.rgutialo.javamqtt.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResultMessageDTOTest {

    public static final String CODE = "CODE";
    public static final String MESSAGE = "MESSAGE";
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";

    @Test
    void builder_shouldCreateResultMessage_whenParametersAreOK() {

        final ResultMessageDTO result = ResultMessageDTO.builder()
                .code(CODE)
                .message(MESSAGE)
                .errorMessage(ERROR_MESSAGE)
                .build();

        assertThat(result.getCode()).isEqualTo(CODE);
        assertThat(result.getMessage()).isEqualTo(MESSAGE);
        assertThat(result.getErrorMessage()).isEqualTo(ERROR_MESSAGE);
    }

    @Test
    void builder_shouldThrowNullPointerException_whenCodeIsNull() {

        assertThrows(NullPointerException.class, () -> {
            ResultMessageDTO.builder()
                    .code(null)
                    .message(MESSAGE)
                    .errorMessage(ERROR_MESSAGE)
                    .build();
        });
    }
}