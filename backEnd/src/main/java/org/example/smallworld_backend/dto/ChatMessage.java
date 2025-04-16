package org.example.smallworld_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String content;
    private String senderId;
    private String receiverId;
    private LocalDateTime timestamp;
    private String senderName;
    private String senderAvatar;
}