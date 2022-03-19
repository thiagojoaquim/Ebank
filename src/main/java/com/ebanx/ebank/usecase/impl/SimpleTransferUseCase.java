package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.entity.TransferReceipt;
import com.ebanx.ebank.entity.repository.AccountRepository;
import com.ebanx.ebank.usecase.AccountEventAction;
import com.ebanx.ebank.usecase.TransferUseCase;
import com.ebanx.ebank.usecase.UseCase;
import com.ebanx.ebank.usecase.event.AccountEvent;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class SimpleTransferUseCase implements TransferUseCase, AccountEventAction<TransferReceipt> {

    private final AccountRepository accountRepository;

    @Override
    public TransferReceipt execute(Long originAccountId, Long destinationAccountId, BigDecimal amount) {
        var originAccount = accountRepository.getById(originAccountId).orElseThrow(() -> new NotFoundException());
        var destinationAccount = accountRepository.getById(destinationAccountId).orElse( new Account(destinationAccountId, BigDecimal.ZERO));
        originAccount.transfer(destinationAccount, amount);
        return new TransferReceipt(originAccount, destinationAccount);
    }

    @Override
    public TransferReceipt execute(AccountEvent event) {
        return execute(event.getOriginAccount(), event.getDestinationAccount(), event.getAmount());
    }
}
