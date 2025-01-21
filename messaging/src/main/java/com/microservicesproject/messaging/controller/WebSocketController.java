package com.microservicesproject.messaging.controller;

import com.microservicesproject.messaging.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate messageTemplate;
    //Broadcasts message to all subscribed websocket clients
    public void sendMessageToClients(Message message){
        log.info("Broadcasting message to WebSocket client: {}", message);

        messageTemplate.convertAndSend("/topic/messages", message);
    }
}
