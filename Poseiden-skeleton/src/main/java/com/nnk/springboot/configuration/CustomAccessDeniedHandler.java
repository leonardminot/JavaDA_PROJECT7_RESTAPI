package com.nnk.springboot.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Custom Access Denied Handler class to handle access denied scenarios.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Handles access denied scenarios by redirecting the user to the error page.
     *
     * @param request The {@link HttpServletRequest} object representing the request.
     *                Here, redirection to an error endpoint.
     * @param response The {@link HttpServletResponse} object representing the response.
     * @param accessDeniedException The {@link AccessDeniedException} thrown when access is denied.
     * @throws IOException If an I/O error occurs during the redirection process.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.sendRedirect("/app/error");
    }
}

