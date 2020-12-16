package com.app.book.web.common.provider;

import com.app.book.web.common.code.ErrorCode;
import com.app.book.web.common.code.RoleCode;
import com.app.book.web.common.exception.ApiException;
import com.app.book.web.common.response.ApiSuccess;
import com.app.book.web.domain.Member;
import com.app.book.web.service.LoginService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;


@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        try {
            ApiSuccess<Member> data = loginService.getMember(id, password);
            Member member = new ObjectMapper().convertValue(data.getData(), new TypeReference<>() {
            });
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(RoleCode.MEMBER.getCode()));

            return new UsernamePasswordAuthenticationToken(member, member.getMemPwd(), grantedAuthorities);
        } catch (ApiException e) {
            throw new UsernameNotFoundException(e.getError().getError().getCode());
        } catch (Exception e) {
            throw new UsernameNotFoundException(ErrorCode.UNKNOWN_ERROR.getCode());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
