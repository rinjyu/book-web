package com.app.book.web.common.exception;

import com.app.book.web.common.response.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

public class ApiException extends RuntimeException {

    @Getter
    protected ApiError error;

    protected ObjectMapper mapper = new ObjectMapper();


    public ApiException(HttpClientErrorException e) throws IOException {
        this.error = mapper.readValue(e.getResponseBodyAsString(), ApiError.class);
    }

    public ApiException(HttpServerErrorException e) throws IOException {
        this.error = mapper.readValue(e.getResponseBodyAsString(), ApiError.class);
    }

    public ApiException(ApiError apiError) {
        this.error = apiError;
    }
}
