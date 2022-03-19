package com.ebanx.ebank.usecase.event;

public interface EventPublisher {

    void publishAccountEvent(AccountEvent event);
}
