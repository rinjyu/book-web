package com.app.book.web.common.response;

import com.app.book.web.common.entity.SuccessEntity;
import lombok.Data;

@Data
public class ApiSuccess<T> {

    private String localDateTime;

    private String status;

    private String message;

    private SuccessEntity success;

    private T data;
}
