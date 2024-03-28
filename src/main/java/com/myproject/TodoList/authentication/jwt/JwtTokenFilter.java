package com.myproject.TodoList.authentication.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.TodoList.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.time.LocalDateTime;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenGenerator jwtTokenGenerator;
    public JwtTokenFilter(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            String token = jwtTokenGenerator.resolveToken((HttpServletRequest) servletRequest);
            if(token != null && jwtTokenGenerator.validateToken(token)){
                Authentication jwtTokenGeneratorAuthentication = jwtTokenGenerator.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(jwtTokenGeneratorAuthentication);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }catch(Exception exception){
            sendErrorResponse(HttpStatus.BAD_REQUEST, (HttpServletResponse) servletResponse, exception);
        }
    }

    private void sendErrorResponse(HttpStatus status, HttpServletResponse response, Exception exception) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        try{
            response.getWriter().write(new ObjectMapper().writeValueAsString(new CustomException(LocalDateTime.now(), status, exception.getMessage())));
        }catch (IOException ioException){

        }
    }
}
