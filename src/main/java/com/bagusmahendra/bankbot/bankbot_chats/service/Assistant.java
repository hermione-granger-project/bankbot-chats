package com.bagusmahendra.bankbot.bankbot_chats.service;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("You are professional Customer service for AwesomeBank")
    String chat(String message);
}
