package com.bagusmahendra.bankbot.bankbot_chats.tool;

import com.bagusmahendra.bankbot.bankbot_chats.repository.AccountsRepository;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import reactor.core.publisher.Mono;

public class AccountInquiryTool {
    private final AccountsRepository accountsRepository;

    public AccountInquiryTool(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    @Tool("Account Balance Inquiry, ask for User Account Number to proceed, the result is in Ringgit Malaysia")
    public double getUserAccountBalance(@P("User Account Number") String accountNumber){
        return accountsRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new RuntimeException("Account not found")))
                .map(account -> account.getBalance())
                .block(); // Blocking to get the double value
    }
}
