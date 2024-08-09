package com.bagusmahendra.bankbot.bankbot_chats.service;

import org.springframework.stereotype.Service;

import com.bagusmahendra.bankbot.bankbot_chats.controller.dto.ChatDTO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ChatsService {
    //bankbot2
    public Mono<ChatDTO> performProcessChat(ChatDTO chatDto){
        log.info("[ChatService] performProcessChat");

        chatDto.setMessage("TOBE Implemented!");
        return Mono.just(chatDto);
    }
}
