package com.myproject.TodoList.exception;

public class InvalidPageException extends RuntimeException{

    public InvalidPageException(String message){
        super(message);
    }
}
