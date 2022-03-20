package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.entity.exception.InvalidDepositValueException;
import com.ebanx.ebank.shared.TestUtil;
import com.ebanx.ebank.usecase.port.input.DepositUseCase;
import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import com.ebanx.ebank.usecase.port.output.repository.impl.SimpleAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SimpleDepositUseCaseTest {

    private AccountRepository accountRepository;
    private DepositUseCase depositUseCase;
    private Long accountId;
    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        accountRepository = new SimpleAccountRepository();
        depositUseCase = new SimpleDepositUseCase(accountRepository);
        accountId = TestUtil.nextLong();
        amount = TestUtil.nextBigDecimal();
    }

    @Test
    void testExecuteNonExistingAccountSuccess() {
        var depositReceipt = depositUseCase.execute(accountId, amount);
        Assertions.assertEquals(accountId.toString(), depositReceipt.getDestination().getId());
        Assertions.assertEquals(amount, depositReceipt.getDestination().getBalance());
        Assertions.assertNotNull(accountRepository.getById(accountId));
    }

    @Test
    void testExecuteExistingAccountSuccess() {
        accountRepository.save(new Account(accountId, amount));
        var depositReceipt = depositUseCase.execute(accountId, amount);
        Assertions.assertEquals(accountId.toString(), depositReceipt.getDestination().getId());
        Assertions.assertEquals(amount.add(amount), depositReceipt.getDestination().getBalance());
    }

    @Test
    void testExecuteInvalidDepositSuccess() {
        Assertions.assertThrows(InvalidDepositValueException.class, () -> {
            depositUseCase.execute(accountId, new BigDecimal(-1));
        });
    }

}
