package com.ebanx.ebank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransferReceipt {
    private Account origin;
    private Account destination;
}
