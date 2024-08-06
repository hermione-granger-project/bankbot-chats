package com.bagusmahendra.bankbot.bankbot_chats.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;

import com.bagusmahendra.bankbot.bankbot_chats.model.Accounts;
import com.bagusmahendra.bankbot.bankbot_chats.model.BillPayments;
import com.bagusmahendra.bankbot.bankbot_chats.repository.AccountsRepository;
import com.bagusmahendra.bankbot.bankbot_chats.repository.BillPaymentsRepository;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BillPaymentTool {
    private final AccountsRepository accountsRepository;
    private final BillPaymentsRepository billPaymentsRepository;
    public BillPaymentTool(AccountsRepository accountsRepository, BillPaymentsRepository billPaymentsRepository) {
        this.accountsRepository = accountsRepository;
        this.billPaymentsRepository = billPaymentsRepository;
    }

    @Tool("Bill Payment processor, Currently only accept Electric Bill, the return value is the transaction ID to tract the transaction status")
    @Transactional
    public String ElectricBillPaymentProcessor(@P("User Account Number") String accountNumber, @P("Amount") double amount){
        log.info("[BillPaymentTool] ElectricBillPaymentProcessor accountNumber:"+accountNumber+" amount:"+amount);
        //Deduct Account Balance
        Accounts userAccountNumber=accountsRepository.findByAccountNumber(accountNumber);
        userAccountNumber.setBalance(userAccountNumber.getBalance()-amount);
        accountsRepository.save(userAccountNumber);
        log.info("New Account Balance: "+userAccountNumber.getBalance());

        //Save Transaction
        BillPayments newPayment=new BillPayments();
        newPayment.setAccountNumber(accountNumber);
        newPayment.setAmount(amount);
        newPayment.setStatus("Pending");

        // Set transaction date to current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        newPayment.setTransactionDate(formattedDateTime);

        // Generate transaction ID: "21" + 4 random digits
        Random random = new Random();
        int randomNumber = random.nextInt(10000); 
        String transactionId = "21" + String.format("%04d", randomNumber);
        newPayment.setTransactionId(transactionId);

        billPaymentsRepository.save(newPayment);
        return transactionId; // Return the generated transaction ID
    }
    
    @Tool("Check Bill Payment Status, ask for Transaction ID to proceed")
    public String BillPaymentStatusChecker(@P("Transaction ID") String transactionId){
        log.info("[BillPaymentTool] BillPaymentStatusChecker transactionId:"+transactionId);
        BillPayments billPayments = billPaymentsRepository.findByTransactionId(transactionId);
        if (billPayments == null){
            throw new RuntimeException("Transaction not found");
        }
        return billPayments.getStatus();
    }
}
