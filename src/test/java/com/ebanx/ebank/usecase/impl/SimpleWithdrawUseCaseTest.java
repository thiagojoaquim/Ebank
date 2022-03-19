package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.entity.exception.InsufficientBalanceException;
import com.ebanx.ebank.entity.exception.InvalidDepositValueException;
import com.ebanx.ebank.shared.TestUtil;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import com.ebanx.ebank.usecase.port.input.WithdrawUseCase;
import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import com.ebanx.ebank.usecase.port.output.repository.impl.SimpleAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SimpleWithdrawUseCaseTest {

    private AccountRepository accountRepository;
    private WithdrawUseCase withdrawUseCase;
    private Long accountId;
    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        accountRepository = new SimpleAccountRepository();
        withdrawUseCase = new SimpleWithdrawUseCase(accountRepository);
        accountId = TestUtil.nextLong();
        amount = TestUtil.nextBigDecimal();
    }

    @Test
    void testExecuteNonExistingAccount() {
        Assertions.assertThrows(NotFoundException.class, () -> withdrawUseCase.execute(accountId, amount));

    }

    @Test
    void testExecuteExistingAccountSuccess() {
        accountRepository.save(new Account(accountId, amount));
        var account = withdrawUseCase.execute(accountId, BigDecimal.ONE);
        Assertions.assertEquals(accountId, account.getId());
        Assertions.assertEquals(amount.subtract(BigDecimal.ONE), account.getBalance());
    }

    @Test
    void testInsufficientBalance() {
        accountRepository.save(new Account(accountId, amount));
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            withdrawUseCase.execute(accountId, amount.multiply(BigDecimal.TEN));
        });
    }

}
