package com.bagusmahendra.bankbot.bankbot_chats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bagusmahendra.bankbot.bankbot_chats.model.Accounts;

public interface AccountsRepository extends MongoRepository<Accounts, String> {
    Accounts findByAccountNumber(String accountNumber);
}
