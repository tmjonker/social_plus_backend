package com.tmjonker.socialmediabackend.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class APIKeyAuthFilter implements Filter {

    private String keyHeader, keyValue;

    public APIKeyAuthFilter(String keyHeader, String keyValue) {

        this.keyValue = keyValue;
        this.keyHeader = keyHeader;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            String apiKey = getApiKey((HttpServletRequest) request);
            if(apiKey != null) {
                if(apiKey.equals(keyValue)) {
                    APIKeyAuthenticationToken apiToken = new APIKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
                    SecurityContextHolder.getContext().setAuthentication(apiToken);
                } else {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(401);
                    httpResponse.getWriter().write("Invalid API Key");
                    return;
                }
            }
        }

        chain.doFilter(request, response);

    }

    private String getApiKey(HttpServletRequest httpServletRequest) {
        String apiKey = null;

        String authHeader = httpServletRequest.getHeader("SocialPlus");
        if(authHeader != null) {
            authHeader = authHeader.trim();
            if(authHeader.toLowerCase().startsWith(keyHeader + " ")) {
                apiKey = authHeader.substring(keyHeader.length()).trim();
            }
        }

        return apiKey;
    }

}
