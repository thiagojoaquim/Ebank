package com.ebanx.ebank.usecase.port.output.receipt;

import com.ebanx.ebank.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferReceipt {
    private AccountExtract origin;
    private AccountExtract destination;

    public TransferReceipt(Account accountOrigin, Account accountDestination) {
        origin = new AccountExtract(accountOrigin);
        destination = new AccountExtract(accountDestination);
    }

}
