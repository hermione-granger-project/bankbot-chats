package com.bagusmahendra.bankbot.bankbot_chats.tool;

import com.bagusmahendra.bankbot.bankbot_chats.model.Accounts;
import com.bagusmahendra.bankbot.bankbot_chats.repository.AccountsRepository;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountInquiryTool {
    private final AccountsRepository accountsRepository;

    public AccountInquiryTool(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    @Tool("Account Balance Inquiry, ask for User Account Number to proceed, the result is in Ringgit Malaysia")
    public double getUserAccountBalance(@P("User Account Number") String accountNumber) {
        log.info("[AccountInquiryTool] getUserAccountBalance accountNumber:"+accountNumber);
        Accounts accounts = accountsRepository.findByAccountNumber(accountNumber);
        if (accounts == null){
            throw new RuntimeException("Account not found");
        }
        return accounts.getBalance();
    }
}
