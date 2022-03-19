package com.ebanx.ebank.usecase.port.input;

import com.ebanx.ebank.usecase.port.output.receipt.TransferReceipt;

import java.math.BigDecimal;

public interface TransferUseCase {

    TransferReceipt execute(final Long sourceAccountId, final Long targetAccountId, final BigDecimal value);
}
