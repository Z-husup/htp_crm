package com.example.htp_crm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/application/**").permitAll() // Paths accessible without authentication
                        .requestMatchers("/expert/**").authenticated() // Path requiring authentication
                        .requestMatchers("/admin/**").authenticated() // Path requiring authentication
                        .anyRequest().authenticated()
                )
                .formLogin();
        return http.build();
    }

}

