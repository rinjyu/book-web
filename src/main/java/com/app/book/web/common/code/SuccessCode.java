package com.app.book.web.common.code;

import lombok.Getter;

public enum SuccessCode {

    SUCCESS("SUCCESS", "성공"),
    SELECT("SELECT", "조회되었습니다."),
    INSERT("INSERT", "등록되었습니다."),
    UPDATE("UPDATE", "수정되었습니다."),
    DELETE("DELETE", "삭제되었습니다."),
    PROCESS("PROCESS", "정상 처리되었습니다.");

    @Getter
    private String code;

    @Getter
    private String message;

    SuccessCode(String code) {
        this.code = code;
    }

    SuccessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
