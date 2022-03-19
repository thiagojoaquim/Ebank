package com.ebanx.ebank.usecase.port.output.repository.impl;

import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;


@Repository
public class SimpleAccountRepository implements AccountRepository {

    private final HashMap<Long, Account> data;

    public SimpleAccountRepository() {
        this.data = new HashMap();
    }

    @Override
    public Optional<Account> getById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Account save(Account account) {
        data.put(account.getId(), account);
        return account;
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
