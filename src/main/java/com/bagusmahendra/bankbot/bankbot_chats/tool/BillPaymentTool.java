package com.bagusmahendra.bankbot.bankbot_chats.tool;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BillPaymentTool {
    @Tool("Bill Payment processor, Currently only accept Electric Bill, the return value is the transaction ID response back the transaction ID remind customer this transaction ID to track the transaction status")
    public String ElectricBillPaymentProcessor(@P("User Account Number") String accountNumber, @P("12-digit electricity account number") String ElectricityAccountNumber, @P("Amount") double amount){
        log.info("[BillPaymentTool] ElectricBillPaymentProcessor accountNumber:"+accountNumber+" ElectricityAccountNumber:"+ElectricityAccountNumber+" amount:"+amount);
        return "23000001";
    }
}
