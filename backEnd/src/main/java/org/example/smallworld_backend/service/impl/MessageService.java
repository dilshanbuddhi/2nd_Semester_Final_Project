package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.dto.ChatMessage;
import org.example.smallworld_backend.entity.Message;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.repo.MessageRepository;
import org.example.smallworld_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public Message saveMessage(ChatMessage chatMessage) {
        User sender = userRepository.findById(UUID.fromString(chatMessage.getSenderId()))
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        
        User receiver = userRepository.findById(UUID.fromString(chatMessage.getReceiverId()))
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        
        Message message = new Message();
        message.setContent(chatMessage.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setRead(false);
        
        return messageRepository.save(message);
    }
    
    @Transactional(readOnly = true)
    public List<ChatMessage> getChatHistory(String user1Id, String user2Id) {
        User user1 = userRepository.findById(UUID.fromString(user1Id))
                .orElseThrow(() -> new RuntimeException("User 1 not found"));
        
        User user2 = userRepository.findById(UUID.fromString(user2Id))
                .orElseThrow(() -> new RuntimeException("User 2 not found"));
        
        List<Message> messages = messageRepository.findMessagesBetweenUsers(user1, user2);
        
        return messages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void markMessagesAsRead(String userId, String senderId) {
        User receiver = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        
        User sender = userRepository.findById(UUID.fromString(senderId))
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        
        List<Message> unreadMessages = messageRepository.findMessagesBetweenUsers(sender, receiver)
                .stream()
                .filter(m -> m.getReceiver().equals(receiver) && !m.isRead())
                .collect(Collectors.toList());
        
        unreadMessages.forEach(m -> m.setRead(true));
        messageRepository.saveAll(unreadMessages);
    }
    
    @Transactional(readOnly = true)
    public int getUnreadMessageCount(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return messageRepository.countUnreadMessages(user);
    }
    
    @Transactional(readOnly = true)
    public List<User> getRecentChatPartners(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return messageRepository.findRecentChatPartners(user);
    }
    
    private ChatMessage convertToDTO(Message message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getContent());
        chatMessage.setSenderId(message.getSender().getUid().toString());
        chatMessage.setReceiverId(message.getReceiver().getUid().toString());
        chatMessage.setTimestamp(message.getTimestamp());
        chatMessage.setSenderName(message.getSender().getFirstName());
        chatMessage.setSenderAvatar("img/avatar2.webp"); // Default avatar

        // Assuming User entity has a profileImage field
       /* if (message.getSender().getProfileImage() != null) {
            chatMessage.setSenderAvatar(message.getSender().getProfileImage());
        } else {
            chatMessage.setSenderAvatar("img/avatar2.webp"); // Default avatar
        }*/
        
        return chatMessage;
    }
}