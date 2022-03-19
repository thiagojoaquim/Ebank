package com.ebanx.ebank.usecase.impl;

import com.ebanx.ebank.entity.Account;

import java.math.BigDecimal;

public interface DepositUseCase {

    public Account execute(Long accountId, BigDecimal value);
}
