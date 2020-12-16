package com.app.book.web.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class ErrorEntity {

    private String code;
    private String message;
    private List<FieldErrorEntity> fieldErrors;
}
