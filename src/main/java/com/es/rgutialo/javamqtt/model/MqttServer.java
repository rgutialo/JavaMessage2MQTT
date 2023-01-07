package com.es.rgutialo.javamqtt.model;

import lombok.Data;

@Data
public class MqttServer {

    private String hostName;
    private String port;
    private String user;
    private String password;
    private int qos;


}
