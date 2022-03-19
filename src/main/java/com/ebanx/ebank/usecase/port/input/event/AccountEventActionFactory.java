package com.ebanx.ebank.usecase.port.input.event;

import com.ebanx.ebank.shared.annotation.Factory;
import com.ebanx.ebank.usecase.impl.SimpleDepositUseCase;
import com.ebanx.ebank.usecase.impl.SimpleTransferUseCase;
import com.ebanx.ebank.usecase.impl.SimpleWithdrawUseCase;
import com.ebanx.ebank.usecase.port.input.AccountEventAction;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Factory
@RequiredArgsConstructor
public class AccountEventActionFactory {

    private Map<EventType, AccountEventAction> eventsMap;

    private final SimpleDepositUseCase depositUseCase;

    private final SimpleTransferUseCase transferUseCase;

    private final SimpleWithdrawUseCase withdrawUseCase;


    @PostConstruct
    void init() {
        eventsMap = new HashMap<>();
        eventsMap.put(EventType.ACCOUNT_DEPOSIT, depositUseCase);
        eventsMap.put(EventType.ACCOUNT_WITHDRAW, withdrawUseCase);
        eventsMap.put(EventType.ACCOUNT_TRANSFER, transferUseCase);
    }

    public AccountEventAction createByType(String name) {
        var eventType = EventType.getByName(name);
        return eventsMap.get(eventType);
    }


}
