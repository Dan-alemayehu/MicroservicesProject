package com.microservicesproject.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicesproject.messaging.controller.WebSocketController;
import com.microservicesproject.messaging.event.SendMessageEvent;
import com.microservicesproject.messaging.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumer {

    private final WebSocketController webSocketController;

    public MessageConsumer(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    @KafkaListener(topics = "messages", groupId = "messaging-group")
    public void consumeMessageEvent(SendMessageEvent messageEvent) {
        log.info("Consuming event from Kafka: {}", messageEvent);

        // Forward the event to WebSocket clients
        // Convert event string to Message object
        Message message = mapToMessage(messageEvent);
        webSocketController.sendMessageToClients(message);
    }

    private Message mapToMessage(SendMessageEvent messageEvent) {
        return Message.builder()
                .senderId(messageEvent.getSenderId())
                .content(messageEvent.getContent())
                .timeStamp(messageEvent.getTimestamp())
                .build();
    }

}

