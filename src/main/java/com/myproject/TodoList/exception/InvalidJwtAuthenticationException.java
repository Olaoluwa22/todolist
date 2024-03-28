package com.myproject.TodoList.exception;

public class InvalidJwtAuthenticationException extends RuntimeException{

    public InvalidJwtAuthenticationException(String message){
        super(message);
    }
}
