package com.es.rgutialo.javamqtt.configuration;


import com.es.rgutialo.javamqtt.model.MqttServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@Slf4j
public class ConfigProperties {

    @Bean
    @ConfigurationProperties(prefix = "mqttserver")
    public MqttServer mqttServer() {
        return new MqttServer();
    }

    @Bean
    @DependsOn("mqttServer")
    public MqttClient mqttClient() throws MqttException {
        String publisherId = "auxiliary_service";
        MqttClient client = new MqttClient("tcp://" + mqttServer().getHostName() + ":" + mqttServer().getPort(), publisherId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttServer().getUser());
        options.setPassword(mqttServer().getPassword().toCharArray());
        try {
            client.connect(options);
            return client;
        } catch (MqttException ex) {
            log.error(ex.getStackTrace().toString());
        }
        return null;
    }
}
