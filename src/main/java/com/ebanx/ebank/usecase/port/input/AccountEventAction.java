package com.ebanx.ebank.usecase.port.input;

import com.ebanx.ebank.usecase.port.input.event.AccountEvent;

public interface AccountEventAction<T> {

    T execute(final AccountEvent event);
}
