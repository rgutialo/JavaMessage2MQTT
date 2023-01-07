package com.es.rgutialo.javamqtt.facades;

import com.es.rgutialo.javamqtt.dto.IncomingMessageDTO;
import com.es.rgutialo.javamqtt.dto.ServerInfoDTO;

public interface MessageFacade {

    public void transformMessage(final IncomingMessageDTO incomingMessage);

    ServerInfoDTO getServerInfo();
}
