package com.app.book.web.controller;

import com.app.book.web.common.handler.CustomLogoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping(value = "logout")
@RequiredArgsConstructor
public class LogoutController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private CustomLogoutHandler customLogoutHandler;

    @Autowired
    public void setCustomLogoutHandler(CustomLogoutHandler customLogoutHandler) {
        this.customLogoutHandler = customLogoutHandler;
    }

    /**
     * 로그아웃
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"", "/"})
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            customLogoutHandler.logout(request, response, authentication);
        }
        redirectStrategy.sendRedirect(request, response, "/login");
    }
}
