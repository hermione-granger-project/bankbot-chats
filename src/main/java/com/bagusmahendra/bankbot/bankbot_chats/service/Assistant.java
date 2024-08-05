package com.bagusmahendra.bankbot.bankbot_chats.service;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {
    @SystemMessage("Answer in Plain text format, limit only 1000 characters")
    String chat(String message);
}
