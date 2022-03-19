package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.shared.TestUtil;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import com.ebanx.ebank.usecase.port.input.TransferUseCase;
import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import com.ebanx.ebank.usecase.port.output.repository.impl.SimpleAccountRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SimpleTransferUseCaseTest {

    private AccountRepository accountRepository;
    private TransferUseCase transferUseCase;
    private Long originAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        accountRepository = new SimpleAccountRepository();
        transferUseCase = new SimpleTransferUseCase(accountRepository);
        originAccountId = TestUtil.nextLong();
        destinationAccountId = TestUtil.nextLong();
        amount = TestUtil.nextBigDecimal();
    }

    @Test
    void testTransferFromExistingAccount() {
        accountRepository.save(new Account(originAccountId, amount));
        var transferReceipt = transferUseCase.execute(originAccountId, destinationAccountId, amount);
        Assertions.assertEquals(originAccountId, transferReceipt.getOrigin().getId());
        Assertions.assertEquals(BigDecimal.ZERO, transferReceipt.getOrigin().getBalance());
        Assertions.assertNotNull(accountRepository.getById(originAccountId));
    }

    @Test
    void testTransferFromNomExistingAccount() {
        Assertions.assertThrows(NotFoundException.class, () -> transferUseCase.execute(originAccountId, destinationAccountId, amount));
    }

    @Test
    void testTransferToExistingAccount() {
        accountRepository.save(new Account(originAccountId, amount));
        accountRepository.save(new Account(destinationAccountId, BigDecimal.ZERO));
        var transferReceipt = transferUseCase.execute(originAccountId, destinationAccountId, amount);
        Assertions.assertEquals(originAccountId, transferReceipt.getOrigin().getId());
        Assertions.assertEquals(BigDecimal.ZERO, transferReceipt.getOrigin().getBalance());
        Assertions.assertEquals(destinationAccountId, transferReceipt.getDestination().getId());
        Assertions.assertEquals(amount, transferReceipt.getDestination().getBalance());
    }
}
