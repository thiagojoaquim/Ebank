package com.ebanx.ebank.usecase.event;

import com.ebanx.ebank.adapter.dto.EventDTO;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum EventType {

    ACCOUNT_TRANSFER("transfer"),
    ACCOUNT_WITHDRAW("withdraw"),
    ACCOUNT_DEPOSIT("deposit");

    @Getter
    private String name;

    public static EventType getByName(String name) {
        return Arrays.stream(EventType.values())
                .filter(type -> type.getName().equals(name)).findFirst().orElseThrow(() -> new NotFoundException());
    }
}
