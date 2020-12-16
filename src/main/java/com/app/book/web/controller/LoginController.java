package com.app.book.web.controller;

import com.app.book.web.common.exception.ApiException;
import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Member;
import com.app.book.web.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping(value = "login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * 로그인
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping({"", "/"})
    public String login(Model model) {

        return "views/login";
    }

    /**
     * 로그인 가능 여부 확인
     * @param dto
     * @return
     * @throws IOException
     */
    @PostMapping("/check")
    public @ResponseBody ResponseEntity<?> loginCheck(@RequestBody Member dto) throws IOException {

        try {
            ApiSuccess<?> data = loginService.getMemberCheck(dto.getMemId(), dto.getMemPwd());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
        }
    }
}
