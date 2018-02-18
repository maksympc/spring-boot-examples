package com.springtutorial.filters;

import com.springtutorial.model.User;
import com.springtutorial.services.TokenAuthService;
import com.springtutorial.services.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StatelessAuthFilter extends GenericFilterBean {
    private final TokenAuthService tokenAuthService;

    public StatelessAuthFilter(@NonNull TokenAuthService service) {
        this.tokenAuthService = service;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(
                tokenAuthService.getAuthentication((HttpServletRequest) request).orElse(null));
        chain.doFilter(request, response);
    }
}
