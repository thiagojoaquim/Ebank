package com.ebanx.ebank.adapter;

import com.ebanx.ebank.usecase.port.output.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reset")
@RestController
@RequiredArgsConstructor
public class ResetController {

    private final AccountRepository accountRepository;

    @PostMapping
    public void reset() {
        accountRepository.deleteAll();
    }
}
