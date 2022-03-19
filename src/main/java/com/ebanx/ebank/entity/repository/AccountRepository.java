package com.ebanx.ebank.entity.repository;

import com.ebanx.ebank.entity.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> getById(Long id);

    Account save(Account account);

}
