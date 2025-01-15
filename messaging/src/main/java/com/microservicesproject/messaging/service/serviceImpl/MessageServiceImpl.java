package com.microservicesproject.messaging.service.serviceImpl;

import com.microservicesproject.messaging.dto.ProfileDto;
import com.microservicesproject.messaging.exceptions.ResourceNotFoundException;
import com.microservicesproject.messaging.feign.ProfileFeignClient;
import com.microservicesproject.messaging.repository.MessageRepository;
import com.microservicesproject.messaging.service.MessageProducer;
import com.microservicesproject.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import com.microservicesproject.messaging.model.Message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageProducer messageProducer;
    private final ProfileFeignClient profileFeignClient;

    @Override
    public Message createMessage(Message message) {

        ProfileDto senderProfile = profileFeignClient.getProfileById(message.getSenderId());
        ProfileDto receiverProfile = profileFeignClient.getProfileById(message.getReceiverId());

        if(senderProfile != null || receiverProfile != null) {
            throw new ResourceNotFoundException("Sender or Receiver profile not found");
        }

        message.setTimeStamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);


        //Send kafka event
        String event = String.format("Message sent from %s to %s: %s",
                senderProfile.getUsername(), receiverProfile.getUsername(), savedMessage.getContent());
        messageProducer.sendMessageEvent(event);

        return savedMessage;
    }

    @Override
    public Message getMessageById(Long id){
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    @Override
    public void deleteMessageById(Long id){
        if(!messageRepository.existsById(id)){
            throw new ResourceNotFoundException("Message not found with id: " + id);
        }
        messageRepository.deleteById(id);
    }

    @Override
    public Message updateMessageContent(Long id, String newContent){
        Message message = messageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
        message.setContent(newContent);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getConversation(Long senderId, Long receiverId){
        ProfileDto senderProfile = profileFeignClient.getProfileById(senderId);
        ProfileDto receiverProfile = profileFeignClient.getProfileById(receiverId);

        if(senderProfile != null || receiverProfile != null) {
            throw new ResourceNotFoundException("Sender or Receiver profile not found");
        }

        log.info("Conversation between {} and {}", senderProfile.getUsername(), receiverProfile.getUsername());

    return messageRepository.findConversationBetweenUsers(senderId, receiverId);
    }

    @Override
    public List<Message> getMessagesForReceiver(Long receiverId) {
        return messageRepository.findByReceiverIdOrderByTimeStampDesc(receiverId);
    }

    @Override
    public void deleteConversation(Long senderId, Long receiverId){
        messageRepository.deleteBySenderAndReceiver(senderId, receiverId);
    }
}