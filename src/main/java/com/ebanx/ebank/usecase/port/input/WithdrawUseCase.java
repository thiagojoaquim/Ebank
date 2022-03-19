package com.ebanx.ebank.usecase.port.input;

import com.ebanx.ebank.entity.Account;

import java.math.BigDecimal;

public interface WithdrawUseCase {

    Account execute(Long accountId, BigDecimal value);

}