package com.app.book.web.common.response;

import com.app.book.web.common.entity.ErrorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {

    private String localDateTime;

    private String status;

    private String message;

    private ErrorEntity error;

}
