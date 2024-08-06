package com.bagusmahendra.bankbot.bankbot_chats.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class Accounts {
    @Id
    private String id;
    private String accountNumber;
    private String name;
    private double balance;
}
