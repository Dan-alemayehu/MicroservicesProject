package com.microservicesproject.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "messages";

    public void sendMessageEvent(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String messageEvent = objectMapper.writeValueAsString(message);
            log.info("Producing event to Kafka: {}", messageEvent);
            kafkaTemplate.send(TOPIC, messageEvent);
        } catch (Exception e){
            throw new RuntimeException("Failed to serialize message", e);
        }
    }
}


