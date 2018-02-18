package com.springtutorial.filters;

import com.springtutorial.model.User;
import com.springtutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminAuthFilter extends GenericFilterBean {
    @Autowired
    private UserService userService;
    private User admin;

    @PostConstruct
    public void init() {
        admin = (User) userService.loadUserByUsername("admin");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(admin));
        chain.doFilter(request, response);
    }
}
