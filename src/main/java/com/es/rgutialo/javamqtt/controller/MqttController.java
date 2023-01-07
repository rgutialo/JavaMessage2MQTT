package com.es.rgutialo.javamqtt.controller;

import com.es.rgutialo.javamqtt.dto.IncomingMessageDTO;
import com.es.rgutialo.javamqtt.dto.ResultMessageDTO;
import com.es.rgutialo.javamqtt.dto.ServerInfoDTO;
import com.es.rgutialo.javamqtt.facades.MessageFacade;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Resource
    private final MessageFacade messageFacade;

    public MqttController(MessageFacade messageFacade) {
        this.messageFacade = messageFacade;
    }


    @PostMapping(path = "mqttSender",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessageDTO> create(@RequestBody IncomingMessageDTO incomingMessage) {

        messageFacade.transformMessage(incomingMessage);

        return null;


    }

    @GetMapping(path = "getServerInfo")
    public ResponseEntity<ServerInfoDTO> getServerInfo() {

        final ServerInfoDTO serverInfo = messageFacade.getServerInfo();

        return ResponseEntity.status(HttpStatus.OK).body(serverInfo);

    }

}
