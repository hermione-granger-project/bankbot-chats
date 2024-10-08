package com.bagusmahendra.bankbot.bankbot_chats.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bagusmahendra.bankbot.bankbot_chats.controller.dto.ChatDTO;
import com.bagusmahendra.bankbot.bankbot_chats.service.ChatsService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/chats")
@Slf4j
public class ChatsController {
    private final ChatsService chatsService;

    public ChatsController(ChatsService chatsService) {
        this.chatsService = chatsService;
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public Mono<ChatDTO> processChat(@RequestBody ChatDTO chatDto){
        log.info("[ChatsController] processChat chatDto="+chatDto.toString());
        return chatsService.performProcessChat(chatDto);
    }
}
