package com.ebanx.ebank.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Random;

public class TestUtil {

    private static final Random GENERATOR = new Random();

    public static Long nextLong() {
        return (GENERATOR.nextInt(950) + 10L);
    }

    public static BigDecimal nextBigDecimal() {
        return new BigDecimal(nextLong());
    }


    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
