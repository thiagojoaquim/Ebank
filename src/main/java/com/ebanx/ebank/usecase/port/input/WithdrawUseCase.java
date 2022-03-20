package com.ebanx.ebank.usecase.port.input;

import com.ebanx.ebank.usecase.port.output.receipt.WithdrawReceipt;

import java.math.BigDecimal;

public interface WithdrawUseCase {

    WithdrawReceipt execute(Long accountId, BigDecimal value);

}
