package com.bagusmahendra.bankbot.bankbot_chats.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bagusmahendra.bankbot.bankbot_chats.model.Accounts;

import reactor.core.publisher.Mono;

public interface AccountsRepository extends ReactiveMongoRepository<Accounts, String> {
    Mono<Accounts> findByAccountNumber(String accountNumber);
}
