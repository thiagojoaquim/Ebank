package com.ebanx.ebank.adapter.controller;

import com.ebanx.ebank.adapter.converter.JsonConverter;
import com.ebanx.ebank.adapter.port.EventDTO;
import com.ebanx.ebank.usecase.port.input.event.AccountEvent;
import com.ebanx.ebank.usecase.port.input.event.AccountEventActionFactory;
import com.ebanx.ebank.usecase.port.input.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/event")
@RestController
@RequiredArgsConstructor
public class EventController {

    private final AccountEventActionFactory factory;
    private final JsonConverter converter;

    @PostMapping
    public ResponseEntity event(@RequestBody EventDTO eventDTO) {
        var resultOfAction = factory
                .createByType(eventDTO.getType()).execute(new AccountEvent(EventType.getByName(eventDTO.getType()),
                        eventDTO.getOrigin(),
                        eventDTO.getDestination(),
                        eventDTO.getAmount()));
        return new ResponseEntity(converter.toJson(resultOfAction), HttpStatus.CREATED);
    }
}
