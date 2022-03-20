package com.ebanx.ebank.adapter.controller;

import com.ebanx.ebank.usecase.port.input.GetBalanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping("/balance")
@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final GetBalanceUseCase getBalanceUseCase;

    @GetMapping
    public BigDecimal getBalance(@RequestParam(name = "account_id") Long id) {
        return getBalanceUseCase.execute(id);
    }
}
