package com.myproject.TodoList.authentication.service;

import com.myproject.TodoList.authentication.userDTO.request.UserSignInRequestDTO;
import com.myproject.TodoList.authentication.userDTO.request.UserSignUpRequestDTO;
import com.myproject.TodoList.authentication.userDTO.response.UserSignInResponseDTO;
import com.myproject.TodoList.authentication.userDTO.response.UserSignUpResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserSignUpResponseDTO signup(UserSignUpRequestDTO userSignUpRequestDTO);
    UserSignInResponseDTO signin(UserSignInRequestDTO userSignInRequestDTO);
}
