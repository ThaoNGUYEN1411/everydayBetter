package co.simplon.everydaybetterbusiness.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class ConvertirUtils {
    public static ObjectMapper getMapper() {
        final var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
