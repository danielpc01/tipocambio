package com.challenge.tipocambio.config;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.challenge.tipocambio.security.token.SessionTokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    private static final String SESSION_TOKEN = "SESSION_TOKEN";

    @Autowired
    private SessionTokenService sessionTokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Skip verification for CORS preflight requests
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            return true;
        }

        String header = request.getHeader(SESSION_TOKEN);
        if (!Optional.ofNullable(header).isPresent()) {
            return false;
        }

        return sessionTokenService.validateSessionToken(header);

    }

}
