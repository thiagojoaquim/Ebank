package com.ebanx.ebank.usecase.port.input;

import java.math.BigDecimal;

public interface GetBalanceUseCase {

    BigDecimal execute(final Long accountId);
}
