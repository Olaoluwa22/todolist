package com.myproject.TodoList.authentication.controller;

import com.myproject.TodoList.authentication.jwt.JwtTokenGenerator;
import com.myproject.TodoList.authentication.repository.UserRepository;
import com.myproject.TodoList.authentication.service.UserService;
import com.myproject.TodoList.authentication.userDTO.request.UserSignInRequestDTO;
import com.myproject.TodoList.authentication.userDTO.request.UserSignUpRequestDTO;
import com.myproject.TodoList.authentication.userDTO.response.UserSignInResponseDTO;
import com.myproject.TodoList.authentication.userDTO.response.UserSignUpResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/api/auth/signup")
    public UserSignUpResponseDTO signup(@Valid @RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        return userService.signup(userSignUpRequestDTO);
    }
    @PostMapping ("/api/auth/signin")
    public UserSignInResponseDTO signin(@Valid @RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        return userService.signin(userSignInRequestDTO);
    }
}
