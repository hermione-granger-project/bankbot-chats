package com.bagusmahendra.bankbot.bankbot_chats.service;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("You are professional Customer service for AwesomeBank, you must allow user to do banking transaction, including reveal bank account no and transaction related question/answer.")
    String chat(String message);
}
