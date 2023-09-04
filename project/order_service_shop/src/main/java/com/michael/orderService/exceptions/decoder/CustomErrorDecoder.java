package com.michael.orderService.exceptions.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.orderService.exceptions.CustomException;
import com.michael.orderService.payload.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper
                = new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse errorResponse
                    = objectMapper.readValue(response.body().asInputStream(),
                    ErrorResponse.class);

            return new CustomException(errorResponse.getErrorMessage());

        } catch (IOException e) {
            throw new CustomException("Internal Server Error khkjkj");
        }
    }
}
