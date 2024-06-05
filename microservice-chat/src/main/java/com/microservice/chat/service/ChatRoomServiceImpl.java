package com.microservice.chat.service;

import com.microservice.chat.persistence.model.ChatRoom;
import com.microservice.chat.persistence.repository.ChatRoomRepository;
import com.microservice.chat.util.UserDetailsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final RestTemplate restTemplate;

    @Override
    public ChatRoom createChatRoom(UUID userId, Set<UUID> supportersId) {
        return null;
    }
}

