package com.ebanx.ebank.usecase.port.output.receipt;

import com.ebanx.ebank.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawReceipt {
    private AccountExtract origin;

    public WithdrawReceipt(Account account) {
        origin = new AccountExtract(account);
    }
}
