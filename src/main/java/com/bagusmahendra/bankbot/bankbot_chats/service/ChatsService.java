package com.bagusmahendra.bankbot.bankbot_chats.service;

import org.springframework.stereotype.Service;

import com.bagusmahendra.bankbot.bankbot_chats.controller.dto.ChatDTO;
import com.bagusmahendra.bankbot.bankbot_chats.repository.AccountsRepository;
import com.bagusmahendra.bankbot.bankbot_chats.tool.AccountInquiryTool;
import com.bagusmahendra.bankbot.bankbot_chats.tool.BillPaymentTool;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ChatsService {
    private final Assistant assistant;
    private static final String PROJECT_ID = "sandbox-431312";
    private static final String LOCATION = "us-central1";
    private static final String MODEL_NAME = "gemini-1.0-pro";

    public ChatsService(AccountsRepository accountsRepository) {
        ChatLanguageModel visionModel = VertexAiGeminiChatModel.builder()
                .project(PROJECT_ID)
                .location(LOCATION)
                .modelName(MODEL_NAME)
                .build();

        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(visionModel)
                .chatMemory(chatMemory)
                .tools(new AccountInquiryTool(accountsRepository), new BillPaymentTool())
                .build();
    }

    public Mono<ChatDTO> performProcessChat(ChatDTO chatDto){
        log.info("[ChatService] performProcessChat");

        chatDto.setMessage(assistant.chat(chatDto.getMessage()));
        return Mono.just(chatDto);
    }
}
