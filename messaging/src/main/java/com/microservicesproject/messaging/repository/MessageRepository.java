package com.microservicesproject.messaging.repository;

import com.microservicesproject.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE (m.senderId = :userId1 AND m.receiverId = :userId2)" +
    "OR (m.senderId = :userId2 AND m.receiverId = :userId1) ORDER BY m.timeStamp ASC")
    List<Message> findConversationBetweenUsers(@Param("userId1") Long userId1,
                                               @Param("userId2") Long userId2);

    @Modifying
    @Query("DELETE FROM Message m WHERE (m.senderId = :userId1 AND m.receiverId = :userId2)" +
    "OR (m.senderId = :userId2 AND m.receiverId = :userId1)")
    void deleteBySenderAndReceiver(@Param("userId1") Long senderId,
                                   @Param("userId2") Long receiverId);

    List<Message> findByReceiverIdOrderByTimeStampDesc(Long receiverId);
}