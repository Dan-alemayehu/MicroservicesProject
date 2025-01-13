package com.microservicesproject.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long senderId;
    @Column(nullable = false)
    private Long receiverId;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false)
    private LocalDateTime timeStamp = LocalDateTime.now();
}
