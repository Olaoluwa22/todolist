package com.myproject.TodoList.authentication.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private JwtTokenGenerator jwtTokenGenerator;
    public JwtConfig(JwtTokenGenerator jwtTokenGenerator){
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public void configure(HttpSecurity httpSecurity){
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenGenerator);
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
