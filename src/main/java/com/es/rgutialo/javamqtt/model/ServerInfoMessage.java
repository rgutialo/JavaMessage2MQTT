package com.es.rgutialo.javamqtt.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ServerInfoMessage {
    private final String hostName;
    private final String port;
    private final String user;
    private final String password;

}
