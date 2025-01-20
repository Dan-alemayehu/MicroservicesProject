package com.microservicesproject.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicesproject.messaging.event.SendMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, SendMessageEvent> kafkaTemplate;

    private static final String TOPIC = "messages";

    public void sendMessageEvent(SendMessageEvent message) {
        try {
            log.info("Producing event to Kafka");
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e){
            throw new RuntimeException("Failed to serialize message", e);
        }
    }
}


