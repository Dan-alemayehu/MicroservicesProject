package com.microservicesproject.messaging.controller;

import com.microservicesproject.messaging.model.Message;
import com.microservicesproject.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class MessagingRest {

    private final MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id){
        log.info("Getting message with ID: {}", id);
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        log.info("Creating message: {}", message);
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> updateMessageContent(@PathVariable Long id, @RequestBody String newContent){
        log.info("Updating content for message with ID: {}", id);
        Message updateMessage = messageService.updateMessageContent(id, newContent);
        return ResponseEntity.ok(updateMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long id){
        log.info("Deleting message with ID: {}", id);
        messageService.deleteMessageById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conversation/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getConversation(@PathVariable Long senderId, @PathVariable Long receiverId){
        log.info("Fetching conversation between sender ID: {} and receiver ID: {}", senderId, receiverId);
        List<Message> conversation = messageService.getConversation(senderId, receiverId);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesForReceiver(@PathVariable Long receiverId){
        log.info("Fetching messages for receiver ID: {}", receiverId);
        List<Message> messages = messageService.getMessagesForReceiver(receiverId);
        return ResponseEntity.ok(messages);
    }

    @DeleteMapping("/conversation/{senderId}/{receiverId}")
    public ResponseEntity<Void> deleteConversation(@PathVariable Long senderId, @PathVariable Long receiverId){
        log.info("Deleting conversation between sender ID: {} and receiver ID: {}", senderId, receiverId);
        messageService.deleteConversation(senderId, receiverId);
        return ResponseEntity.noContent().build();
    }
}