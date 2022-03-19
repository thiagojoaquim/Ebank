package com.ebanx.ebank.entity;


import lombok.Getter;

import java.math.BigDecimal;

public class Account {

    private Long id;

    @Getter
    private BigDecimal balance;

    public void deposit(BigDecimal value) {
        balance = balance.add(value);
    }

    public void withdraw(BigDecimal value) {
        balance = balance.subtract(value);
    }
}
