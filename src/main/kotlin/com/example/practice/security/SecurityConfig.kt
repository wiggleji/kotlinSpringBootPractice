package com.example.practice.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = true)
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .formLogin().disable() // disable form Login
                .csrf().disable() // disable CSRF

        http.authorizeHttpRequests()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/posts/**").permitAll()
                .anyRequest().permitAll() // temporarily permit all request before allowing security

        return http.build()
    }
}