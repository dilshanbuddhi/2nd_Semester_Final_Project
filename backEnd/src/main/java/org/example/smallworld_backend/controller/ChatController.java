package org.example.smallworld_backend.controller;

import org.example.smallworld_backend.dto.ApiResponse;
import org.example.smallworld_backend.dto.ChatMessage;
import org.example.smallworld_backend.entity.Message;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.repo.MessageRepository;
import org.example.smallworld_backend.repo.UserRepository;
import org.example.smallworld_backend.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepo;

    @MessageMapping("/chat")
    public void handleChat(@Payload ChatMessage chatMessage) {
        System.out.println("Received message: " + chatMessage.getSenderId() + " -> " +
                chatMessage.getReceiverId() + ": " + chatMessage.getContent());

        // Set current timestamp if not provided
        if (chatMessage.getTimestamp() == null) {
            chatMessage.setTimestamp(LocalDateTime.now());
        }

        // Save the message to database
        Message savedMessage = messageService.saveMessage(chatMessage);

        // Fetch additional data like sender name and avatar
        User sender = userRepo.findById(UUID.fromString(chatMessage.getSenderId())).orElseThrow();
        chatMessage.setSenderName(sender.getFirstName());
        chatMessage.setSenderAvatar("img/avatar2.webp");

        // Send message to recipient's topic
        messagingTemplate.convertAndSend("/topic/chat/" + chatMessage.getReceiverId(), chatMessage);
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<ChatMessage>>> getChatHistory(
            @RequestParam("user1") String user1Id,
            @RequestParam("user2") String user2Id) {

        List<ChatMessage> messages = messageService.getChatHistory(user1Id, user2Id);

        ApiResponse<List<ChatMessage>> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Chat history retrieved successfully");
        response.setData(messages);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/mark-read")
    public ResponseEntity<ApiResponse<Void>> markMessagesAsRead(
            @RequestParam("userId") String userId,
            @RequestParam("senderId") String senderId) {

        messageService.markMessagesAsRead(userId, senderId);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Messages marked as read");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/unread-count")
    public ResponseEntity<ApiResponse<Integer>> getUnreadCount(
            @RequestParam("userId") String userId) {

        int count = messageService.getUnreadMessageCount(userId);

        ApiResponse<Integer> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Unread message count retrieved");
        response.setData(count);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/chat-partners")
    public ResponseEntity<ApiResponse<List<User>>> getRecentChatPartners(
            @RequestParam("userId") String userId) {

        List<User> partners = messageService.getRecentChatPartners(userId);

        ApiResponse<List<User>> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Recent chat partners retrieved");
        response.setData(partners);

        return ResponseEntity.ok(response);
    }
}