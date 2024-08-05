package com.bagusmahendra.bankbot.bankbot_chats.tool;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class AccountInquiryTool {

    @Tool("Account Balance Inquiry, ask for User Account Number to proceed, the result is in Ringgit Malaysia")
    public double getUserAccountBalance(@P("User Account Number") String accountNumber){
        return 98353.00;
    }
}
