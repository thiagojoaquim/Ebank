package com.ebanx.ebank.adapter;

import com.ebanx.ebank.adapter.dto.EventDTO;
import com.ebanx.ebank.adapter.event.AccountEventActionFactory;
import com.ebanx.ebank.usecase.event.AccountEvent;
import com.ebanx.ebank.usecase.event.EventType;
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

    @PostMapping
    public ResponseEntity event(@RequestBody EventDTO eventDTO) {
        var resultOfAction = factory
                .createByType(eventDTO.getType()).execute(new AccountEvent(EventType.getByName(eventDTO.getType()),
                        eventDTO.getOrigin(),
                        eventDTO.getDestination(),
                        eventDTO.getAmount()));
        return new ResponseEntity(resultOfAction, HttpStatus.CREATED);
    }
}
