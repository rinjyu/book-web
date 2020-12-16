package com.app.book.web.service.impl;

import com.app.book.web.common.exception.ApiException;
import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Member;
import com.app.book.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${url.book-api}")
    private String BOOK_API;

    private RestTemplate apiRestTemplate;

    @Autowired
    @Qualifier("apiRestTemplate")
    public void setApiRestTemplate(RestTemplate restTemplate) {
        this.apiRestTemplate = restTemplate;
    }

    @Override
    public ApiSuccess<Member> getMember(String memId, String memPwd) throws IOException {

        ApiSuccess<Member> data = null;
        try {
            Member dto = new Member();
            dto.setMemId(memId);
            dto.setMemPwd(memPwd);

            ResponseEntity<ApiSuccess> responseEntity = apiRestTemplate.postForEntity(BOOK_API + "/api/member", dto, ApiSuccess.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                data = responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            throw new ApiException(e);
        } catch (HttpServerErrorException e) {
            throw new ApiException(e);
        }

        return data;
    }


    @Override
    public ApiSuccess<?> getMemberCheck(String memId, String memPwd) throws IOException {

        ApiSuccess<?> data = null;
        try {
            Member dto = new Member();
            dto.setMemId(memId);
            dto.setMemPwd(memPwd);

            ResponseEntity<ApiSuccess> responseEntity = apiRestTemplate.postForEntity(BOOK_API + "/api/member/check", dto, ApiSuccess.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                data = responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            throw new ApiException(e);
        } catch (HttpServerErrorException e) {
            throw new ApiException(e);
        }

        return data;
    }
}
