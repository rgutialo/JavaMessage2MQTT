package com.es.rgutialo.javamqtt.dto;

import io.micrometer.common.util.StringUtils;
import lombok.Builder;
import lombok.Data;

import java.util.Base64;

/**
 * Manages DTO info related to display MQTT server details
 */
@Builder
@Data
public class ServerInfoDTO {

    private static final String DEFAULT_HOST = "localhost";

    private static final String DEFAULT_PORT = "1883";

    private final String hostName;
    private final String port;
    private final String user;
    private final String password;


    /**
     * Override lombok getPassword method. Not so secure, but more secure than plain text
     *
     * @return {@link String} Password encoded in Base64
     */
    public String getPassword() {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String getHostName() {
        return StringUtils.isNotEmpty(this.hostName) ? this.hostName : DEFAULT_HOST;
    }

    public String getPort() {
        return StringUtils.isNotEmpty(this.port) ? this.port : DEFAULT_PORT;
    }

}
