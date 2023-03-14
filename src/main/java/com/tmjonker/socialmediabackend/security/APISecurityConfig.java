package com.tmjonker.socialmediabackend.security;

import com.tmjonker.socialmediabackend.filters.APIKeyAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class APISecurityConfig {

    @Value("${tmjonker.http.auth-token-header-name}")
    private String keyHeader;

    @Value("${tmjonker.http.auth-token}")
    private String keyValue;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers(("/api/**"))
                            .authenticated();
                })
                .addFilterBefore(new APIKeyAuthFilter(keyHeader, keyValue), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests().anyRequest().authenticated();

        return http.build();

    }
}
