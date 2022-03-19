package com.ebanx.ebank.entity;


import com.ebanx.ebank.entity.exception.InsufficientBalanceException;
import com.ebanx.ebank.entity.exception.InvalidDepositValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1L, BigDecimal.ZERO);
    }

    @Test
    void testDepositWithSuccess() {
        var currentBalance = account.getBalance();
        account.deposit(new BigDecimal(100));
        Assertions.assertEquals(currentBalance.add(new BigDecimal(100)), account.getBalance());
    }

    @Test
    void testWithdrawWithSuccess() {
        var currentBalance = account.getBalance();
        account.deposit(new BigDecimal(100));
        Assertions.assertEquals(currentBalance.add(new BigDecimal(100)), account.getBalance());
    }

    @Test
    void testDepositInvalidAmount() {
        Assertions.assertThrows(InvalidDepositValueException.class, () -> account.deposit(new BigDecimal(-100)));
    }

    @Test
    void testWithdrawInsufficientBalance() {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> account.withdraw(new BigDecimal(100)));
    }


}
