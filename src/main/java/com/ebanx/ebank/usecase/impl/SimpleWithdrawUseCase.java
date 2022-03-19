package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.usecase.port.output.receipt.WithdrawReceipt;
import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import com.ebanx.ebank.usecase.port.input.AccountEventAction;
import com.ebanx.ebank.usecase.port.input.UseCase;
import com.ebanx.ebank.usecase.port.input.WithdrawUseCase;
import com.ebanx.ebank.usecase.port.input.event.AccountEvent;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class SimpleWithdrawUseCase implements WithdrawUseCase, AccountEventAction<WithdrawReceipt> {

    private final AccountRepository accountRepository;

    @Override
    public Account execute(Long accountId, BigDecimal value) {
        var account = accountRepository.getById(accountId).orElseThrow(() -> new NotFoundException());
        account.withdraw(value);
        return account;
    }

    @Override
    public WithdrawReceipt execute(AccountEvent accountEvent) {
        return new WithdrawReceipt(execute(accountEvent.getOriginAccount(), accountEvent.getAmount()));
    }
}
