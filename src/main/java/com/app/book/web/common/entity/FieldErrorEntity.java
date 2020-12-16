package com.app.book.web.common.entity;

import lombok.Data;

@Data
public class FieldErrorEntity {

    private String object;
    private String field;
    private String reason;
}
