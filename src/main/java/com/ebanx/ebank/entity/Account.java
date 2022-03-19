package com.ebanx.ebank.entity;


import com.ebanx.ebank.entity.exception.InsufficientBalanceException;
import com.ebanx.ebank.entity.exception.InvalidDepositValueException;
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
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDepositValueException();
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        balance = balance.subtract(amount);
    }

    public void transfer(Account to, BigDecimal amount) {
        this.withdraw(amount);
        to.deposit(amount);
    }
}
