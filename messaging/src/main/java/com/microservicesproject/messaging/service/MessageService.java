package com.microservicesproject.messaging.service;

import com.microservicesproject.messaging.model.Message;

import java.util.List;

public interface MessageService {
    //Message Methods
    Message createMessage(Message message);
    Message getMessageById(Long id);
    void deleteMessageById(Long id);
    Message updateMessageContent(Long id, String content);

    //Conversation Methods
    List<Message> getConversation(Long senderId, Long receiverId);
    List<Message> getMessagesForReceiver(Long receiverId);
    void deleteConversation(Long id, Long receiverId);
}
