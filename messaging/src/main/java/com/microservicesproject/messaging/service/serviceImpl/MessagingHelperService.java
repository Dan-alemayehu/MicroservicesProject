package com.microservicesproject.messaging.service.serviceImpl;

import com.microservicesproject.messaging.dto.ProfileDto;
import com.microservicesproject.messaging.feign.ProfileFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingHelperService {

    private final ProfileFeignClient profileFeignClient;

    public ProfileDto getSenderProfile(long senderId){
        return profileFeignClient.getProfileById(senderId);
    }

    public ProfileDto getReceiverProfile(long receiverId){
        return profileFeignClient.getProfileById(receiverId);
    }
}
