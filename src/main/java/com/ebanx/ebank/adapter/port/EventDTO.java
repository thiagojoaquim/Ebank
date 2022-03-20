package com.ebanx.ebank.adapter.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String type;
    private Long origin;
    private BigDecimal amount;
    private Long destination;
}
