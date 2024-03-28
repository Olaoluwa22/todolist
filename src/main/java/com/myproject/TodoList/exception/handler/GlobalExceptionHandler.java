package com.myproject.TodoList.exception.handler;

import com.myproject.TodoList.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.NOT_FOUND, badRequestException.getMessage());
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<Object> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException invalidJwtAuthenticationException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, invalidJwtAuthenticationException.getMessage());
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<Object> handleInvalidPageException(InvalidPageException invalidPageException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, invalidPageException.getMessage());
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.NOT_FOUND, resourceNotFoundException.getMessage());
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, badCredentialsException.getMessage());
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, "Expired or Invalid JWT Token");
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessage = new ArrayList<>();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            errorMessage.add(fieldError.getDefaultMessage());
        }
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, errorMessage.toString().substring(1, errorMessage.toString().length()-1));
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, "Invalid input");
        return ResponseEntityBuilder.build(customException);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception){
        CustomException customException = new CustomException(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        return ResponseEntityBuilder.build(customException);
    }
}