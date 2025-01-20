package com.microservicesproject.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageEvent implements Serializable {
    private Long senderId;
    private String content;
    private LocalDateTime timestamp;

}
