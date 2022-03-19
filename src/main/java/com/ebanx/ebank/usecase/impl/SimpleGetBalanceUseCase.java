package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import com.ebanx.ebank.usecase.port.input.GetBalanceUseCase;
import com.ebanx.ebank.usecase.port.input.UseCase;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class SimpleGetBalanceUseCase implements GetBalanceUseCase {

    private final AccountRepository accountRepository;

    @Override
    public BigDecimal execute(final Long accountId) {
        var possibleAccount = accountRepository.getById(accountId);
        return possibleAccount.orElseThrow(() -> new NotFoundException()).getBalance();
    }
}
