package com.ebanx.ebank.usecase;

import java.math.BigDecimal;

public interface GetBalanceUseCase {

    BigDecimal execute(Long accountId);
}
