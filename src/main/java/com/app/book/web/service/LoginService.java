package com.app.book.web.service;

import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Member;

import java.io.IOException;

public interface LoginService {

    ApiSuccess<Member> getMember(String memId, String memPwd) throws IOException;

    ApiSuccess<?> getMemberCheck(String memId, String memPwd) throws IOException;
}
