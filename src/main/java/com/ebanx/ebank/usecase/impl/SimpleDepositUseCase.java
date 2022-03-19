package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.entity.DepositReceipt;
import com.ebanx.ebank.entity.repository.AccountRepository;
import com.ebanx.ebank.usecase.AccountEventAction;
import com.ebanx.ebank.usecase.UseCase;
import com.ebanx.ebank.usecase.event.AccountEvent;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@UseCase
public class SimpleDepositUseCase implements AccountEventAction<DepositReceipt>, DepositUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Account execute(Long accountId, BigDecimal value) {
        var account = accountRepository.getById(accountId).orElse(new Account(accountId, BigDecimal.ZERO));
        account.deposit(value);
        accountRepository.save(account);
        return account;
    }

    @Override
    public DepositReceipt execute(AccountEvent event) {
        return new DepositReceipt(execute(event.getDestinationAccount(), event.getAmount()));
    }
}
