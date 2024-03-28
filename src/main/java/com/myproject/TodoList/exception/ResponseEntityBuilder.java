package com.myproject.TodoList.exception;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(CustomException customException){
        return new ResponseEntity<>(customException, customException.getStatus());
    }

}
