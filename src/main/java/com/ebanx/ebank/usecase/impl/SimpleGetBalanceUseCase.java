package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.repository.AccountRepository;
import com.ebanx.ebank.usecase.GetBalanceUseCase;
import com.ebanx.ebank.usecase.UseCase;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class SimpleGetBalanceUseCase implements GetBalanceUseCase {

    private final AccountRepository accountRepository;

    @Override
    public BigDecimal execute(Long accountId) {
        var possibleAccount = accountRepository.getById(accountId);
        return possibleAccount.orElseThrow(() -> new NotFoundException()).getBalance();
    }
}
