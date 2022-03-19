package com.ebanx.ebank.usecase.port.input.event;

public interface EventPublisher {

    void publishAccountEvent(AccountEvent event);
}
