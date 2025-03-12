package com.mypackage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/products").permitAll() // Allow unauthenticated access to /api/products
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin().disable() // Disables form login if you don't need it
                .httpBasic().disable() // Optionally, disable HTTP basic authentication if not needed
                .logout().disable(); // Disable logout if you're managing logout manually or need custom handling

        return http.build();
    }
}
