package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.usecase.port.output.receipt.DepositReceipt;
import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import com.ebanx.ebank.usecase.port.input.AccountEventAction;
import com.ebanx.ebank.usecase.port.input.DepositUseCase;
import com.ebanx.ebank.usecase.port.input.UseCase;
import com.ebanx.ebank.usecase.port.input.event.AccountEvent;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@UseCase
public class SimpleDepositUseCase implements AccountEventAction<DepositReceipt>, DepositUseCase {

    private final AccountRepository accountRepository;

    @Override
    public DepositReceipt execute(Long accountId, BigDecimal value) {
        var account = accountRepository.getById(accountId).orElse(new Account(accountId, BigDecimal.ZERO));
        account.deposit(value);
        accountRepository.save(account);
        return new DepositReceipt(account);
    }

    @Override
    public DepositReceipt execute(AccountEvent event) {
        return execute(event.getDestinationAccount(), event.getAmount());
    }
}
