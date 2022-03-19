package com.ebanx.ebank.usecase;

import com.ebanx.ebank.entity.TransferReceipt;

import java.math.BigDecimal;

public interface TransferUseCase {

    TransferReceipt execute(Long sourceAccountId, Long targetAccountId, BigDecimal value);
}
