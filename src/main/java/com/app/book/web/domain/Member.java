package com.app.book.web.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class Member {

    private String memId;

    private String memNm;

    private String memPwd;

    private String memEmail;

    private String lastLoginDts;

    private String regDts;

    private String regId;

    private String modDts;

    private String modId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
