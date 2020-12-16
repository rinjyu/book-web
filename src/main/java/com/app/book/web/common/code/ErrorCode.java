package com.app.book.web.common.code;

import lombok.Getter;

public enum ErrorCode {

    FAIL("FAIL", "실패"),
    INVALID_FIELD("INVALID_FIELD", "유효하지 않은 필드"),
    NOT_FOUND("NOT_FOUND", "존재하지 않은 데이터"),
    DUPLICATE_DATA("DUPLICATE_DATA", "중복 데이터 존재"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "알수없는 오류");

    @Getter
    private String code;

    @Getter
    private String message;

    ErrorCode(String code) {
        this.code = code;
    }

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
