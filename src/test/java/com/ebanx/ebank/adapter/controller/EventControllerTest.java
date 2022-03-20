package com.ebanx.ebank.adapter.controller;

import com.ebanx.ebank.adapter.port.EventDTO;
import com.ebanx.ebank.entity.Account;
import com.ebanx.ebank.shared.TestUtil;
import com.ebanx.ebank.usecase.impl.SimpleDepositUseCase;
import com.ebanx.ebank.usecase.impl.SimpleTransferUseCase;
import com.ebanx.ebank.usecase.impl.SimpleWithdrawUseCase;
import com.ebanx.ebank.usecase.port.input.event.AccountEvent;
import com.ebanx.ebank.usecase.port.input.event.AccountEventActionFactory;
import com.ebanx.ebank.usecase.port.output.receipt.TransferReceipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;


@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountEventActionFactory factory;

    @MockBean
    private SimpleTransferUseCase simpleTransferUseCase;

    @MockBean
    private SimpleWithdrawUseCase simpleWithdrawUseCase;

    @MockBean
    private SimpleDepositUseCase simpleDepositUseCase;

    private AccountEvent event;

    private Account origin;

    private Account destination;

    @BeforeEach
    void setUp() {
        origin = new Account(TestUtil.nextLong(), TestUtil.nextBigDecimal());
        destination = new Account(TestUtil.nextLong(), BigDecimal.ZERO);

    }

    @Test
    void testTransferEventWithSuccess() throws Exception {
        Mockito.when(factory.createByType("transfer")).thenReturn(simpleTransferUseCase);
        Mockito.when(simpleTransferUseCase.execute(event)).thenReturn(new TransferReceipt(origin, destination));
        mockMvc.perform(MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.asJsonString(new EventDTO("transfer", origin.getId(), origin.getBalance(), destination.getId()))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testDepositEventWithSuccess() throws Exception {
        Mockito.when(factory.createByType("deposit")).thenReturn(simpleTransferUseCase);
        Mockito.when(simpleTransferUseCase.execute(event)).thenReturn(new TransferReceipt(origin, destination));
        mockMvc.perform(MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.asJsonString(new EventDTO("deposit", null, origin.getBalance(), destination.getId()))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testWithdrawEventWithSuccess() throws Exception {
        Mockito.when(factory.createByType("withdraw")).thenReturn(simpleTransferUseCase);
        Mockito.when(simpleTransferUseCase.execute(event)).thenReturn(new TransferReceipt(origin, destination));
        mockMvc.perform(MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.asJsonString(new EventDTO("withdraw", origin.getId(), origin.getBalance(),null))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}
