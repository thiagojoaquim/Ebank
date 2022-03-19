package com.ebanx.ebank.usecase;

import com.ebanx.ebank.usecase.event.AccountEvent;

public interface AccountEventAction<T> {

    T execute(AccountEvent event);
}
