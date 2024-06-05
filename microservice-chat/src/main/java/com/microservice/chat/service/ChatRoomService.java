package com.microservice.chat.service;

import com.microservice.chat.persistence.model.ChatRoom;

import java.util.Set;
import java.util.UUID;

public interface ChatRoomService {
    ChatRoom createChatRoom(UUID userIds, Set<UUID> supportersId );
}
