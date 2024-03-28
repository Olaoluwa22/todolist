package com.myproject.TodoList.authentication.controller;

import com.myproject.TodoList.authentication.jwt.JwtTokenGenerator;
import com.myproject.TodoList.authentication.repository.UserRepository;
import com.myproject.TodoList.authentication.service.UserService;
import com.myproject.TodoList.authentication.userDTO.request.UserSignInRequestDTO;
import com.myproject.TodoList.authentication.userDTO.request.UserSignUpRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping ("/api/auth/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        return userService.signin(userSignInRequestDTO);
    }
    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        return userService.signup(userSignUpRequestDTO);
    }
}
