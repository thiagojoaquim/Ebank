package com.ebanx.ebank.adapter.controller;

import com.ebanx.ebank.shared.TestUtil;
import com.ebanx.ebank.usecase.exception.NotFoundException;
import com.ebanx.ebank.usecase.impl.SimpleGetBalanceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpleGetBalanceUseCase getBalanceUseCase;

    private Long accountId;

    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        accountId = TestUtil.nextLong();
        amount = TestUtil.nextBigDecimal();
    }

    @Test
    void getBalanceWithSuccess() throws Exception {
        Mockito.when(getBalanceUseCase.execute(accountId)).thenReturn(amount);
        mockMvc.perform(MockMvcRequestBuilders.get("/balance").param("account_id", accountId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(amount.toString()));
    }

    @Test
    void getBalanceUserNotFound() throws Exception {
        Mockito.when(getBalanceUseCase.execute(accountId)).thenThrow(new NotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/balance").param("account_id", accountId.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(BigDecimal.ZERO.toString()));
    }
}
