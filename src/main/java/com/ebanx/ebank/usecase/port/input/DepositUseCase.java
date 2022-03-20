package com.ebanx.ebank.usecase.port.input;

import com.ebanx.ebank.usecase.port.output.receipt.DepositReceipt;

import java.math.BigDecimal;

public interface DepositUseCase {

    DepositReceipt execute(Long accountId, BigDecimal value);
}
