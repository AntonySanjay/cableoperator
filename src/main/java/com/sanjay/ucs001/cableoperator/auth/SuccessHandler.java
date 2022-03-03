package com.sanjay.ucs001.cableoperator.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        if (currentAuth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectStrategy.sendRedirect(request, response, "/admin");
        } else {
            redirectStrategy.sendRedirect(request, response, "/user");
        }

    }
}
