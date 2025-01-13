package com.microservicesproject.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicesproject.messaging.controller.WebSocketController;
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
    public void consumeMessageEvent(String messageEvent) {
        log.info("Consuming event from Kafka: {}", messageEvent);

        // Forward the event to WebSocket clients
        Message message = parseMessageEvent(messageEvent); // Convert event string to Message object
        webSocketController.sendMessageToClients(message);
    }

    private Message parseMessageEvent(String messageEvent) {
        // Parse the event string to reconstruct the Message object
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(messageEvent, Message.class);
        } catch (Exception e){
            throw new RuntimeException("Failed to parse message event", e);
        }
    }
}

