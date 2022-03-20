package com.ebanx.ebank.usecase.port.output.receipt;

import com.ebanx.ebank.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DepositReceipt {
    private AccountExtract destination;

    public DepositReceipt(Account account) {
        destination = new AccountExtract(account);
    }

}
