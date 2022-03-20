package com.ebanx.ebank.usecase.port.output.receipt;

import com.ebanx.ebank.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountExtract {

    private String id;
    private BigDecimal balance;

    public AccountExtract(Account account) {
        this.id = account.getId().toString();
        balance = account.getBalance();
    }
}
