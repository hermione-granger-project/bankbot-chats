package com.bagusmahendra.bankbot.bankbot_chats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bagusmahendra.bankbot.bankbot_chats.model.BillPayments;

public interface BillPaymentsRepository extends MongoRepository<BillPayments, String> {
    BillPayments findByTransactionId(String transactionId);
}
