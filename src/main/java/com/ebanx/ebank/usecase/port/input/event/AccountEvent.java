package com.ebanx.ebank.usecase.port.input.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountEvent {
    private EventType eventType;
    private Long originAccount;
    private Long destinationAccount;
    private BigDecimal amount;
}
