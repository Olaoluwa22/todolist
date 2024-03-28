package com.myproject.TodoList.authentication.service;

import com.myproject.TodoList.authentication.userDTO.request.UserSignInRequestDTO;
import com.myproject.TodoList.authentication.userDTO.request.UserSignUpRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> signup(UserSignUpRequestDTO userSignUpRequestDTO);
    ResponseEntity<?> signin(UserSignInRequestDTO userSignInRequestDTO);
}
