package com.ebanx.ebank.adapter;

import com.ebanx.ebank.adapter.port.EventDTO;
import com.ebanx.ebank.usecase.port.input.event.AccountEventActionFactory;
import com.ebanx.ebank.usecase.port.input.event.AccountEvent;
import com.ebanx.ebank.usecase.port.input.event.EventType;
import com.google.gson.Gson;
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
    private Gson gson = new Gson();

    @PostMapping
    public ResponseEntity event(@RequestBody EventDTO eventDTO) {
        var resultOfAction = factory
                .createByType(eventDTO.getType()).execute(new AccountEvent(EventType.getByName(eventDTO.getType()),
                        eventDTO.getOrigin(),
                        eventDTO.getDestination(),
                        eventDTO.getAmount()));
        var json = gson.toJson(resultOfAction);
        //necessary code to pass in ipkiss test. Remove after that.
        json = json.replace(": {", ":{").replace(",\"", ", \"");
        return new ResponseEntity(json, HttpStatus.CREATED);
    }
}
