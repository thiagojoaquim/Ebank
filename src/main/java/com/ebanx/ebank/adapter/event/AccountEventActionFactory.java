package com.ebanx.ebank.adapter.event;

import com.ebanx.ebank.usecase.AccountEventAction;
import com.ebanx.ebank.usecase.event.AccountEvent;
import com.ebanx.ebank.usecase.event.EventType;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import com.ebanx.ebank.usecase.impl.DepositUseCase;
import com.ebanx.ebank.usecase.impl.SimpleDepositUseCase;
import com.ebanx.ebank.usecase.impl.SimpleTransferUseCase;
import com.ebanx.ebank.usecase.impl.SimpleWithdrawUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
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
        var eventType = Arrays.stream(EventType.values())
                .filter(type -> type.getName().equals(name)).findFirst().orElseThrow(() -> new NotFoundException());
        return eventsMap.get(eventType);
    }


}
