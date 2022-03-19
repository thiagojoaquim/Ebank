package com.ebanx.ebank.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Account {

    private Long id;

    private BigDecimal balance;

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void transfer(Account to, BigDecimal amount) {
        this.withdraw(amount);
        to.deposit(amount);
    }
}
