package com.app.book.web.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class Book {

    private int bookNo;

    private String bookNm;

    private int bookPrice;

    private String bookAuthor;

    private String bookPublisher;

    private String bookDesc;

    private String bookImgPath;

    private String regDts;

    private String regId;

    private String modDts;

    private String modId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
