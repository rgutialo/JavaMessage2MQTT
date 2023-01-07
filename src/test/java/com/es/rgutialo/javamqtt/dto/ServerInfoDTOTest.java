package com.es.rgutialo.javamqtt.dto;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

class ServerInfoDTOTest {

    public static final String HOST_NAME = "HOST_NAME";
    public static final String PORT = "PORT";
    public static final String USER = "USER";
    public static final String PASSWORD = "PASSWORD";
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_MQTT_PORT = "1883";


    @Test
    void builder_shouldCreateHostWithDefaultHost_whenHostNameIsNull() {

        final var result = ServerInfoDTO.builder()
                .hostName(null)
                .port(PORT)
                .user(USER)
                .password(PASSWORD)
                .build();

        assertThat(result.getHostName()).isEqualTo(DEFAULT_HOST);
        assertThat(result.getPort()).isEqualTo(PORT);
        assertThat(result.getUser()).isEqualTo(USER);
        assertThat(result.getPassword()).isEqualTo(Base64.getEncoder().encodeToString(PASSWORD.getBytes()));

    }

    @Test
    void builder_shouldCreateHostWithDefaultHost_whenHostNameIsNull2() {

        final var result = ServerInfoDTO.builder()
                .port(PORT)
                .user(USER)
                .password(PASSWORD)
                .build();

        assertThat(result.getHostName()).isEqualTo(DEFAULT_HOST);
        assertThat(result.getPort()).isEqualTo(PORT);
        assertThat(result.getUser()).isEqualTo(USER);
        assertThat(result.getPassword()).isEqualTo(Base64.getEncoder().encodeToString(PASSWORD.getBytes()));

    }

    @Test
    void builder_shouldCreatePortWithDefaultMqttPort_whenPortIsNull() {

        final var result = ServerInfoDTO.builder()
                .hostName(HOST_NAME)
                .port(null)
                .user(USER)
                .password(PASSWORD)
                .build();

        assertThat(result.getHostName()).isEqualTo(HOST_NAME);
        assertThat(result.getPort()).isEqualTo(DEFAULT_MQTT_PORT);
        assertThat(result.getUser()).isEqualTo(USER);
        assertThat(result.getPassword()).isEqualTo(Base64.getEncoder().encodeToString(PASSWORD.getBytes()));

    }

    @Test
    public void getPassword_shouldReturnPasswordEncoded_whenPasswordIsNotNull() {

        final var result = ServerInfoDTO.builder()
                .hostName(HOST_NAME)
                .port(PORT)
                .user(USER)
                .password(PASSWORD)
                .build();

        assertThat(result.getPassword()).isEqualTo(Base64.getEncoder().encodeToString(PASSWORD.getBytes()));

    }
}