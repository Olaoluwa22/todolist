package com.myproject.TodoList.authentication.serviceImpl;

import com.myproject.TodoList.authentication.jwt.JwtTokenGenerator;
import com.myproject.TodoList.authentication.model.User;
import com.myproject.TodoList.authentication.repository.UserRepository;
import com.myproject.TodoList.authentication.service.UserService;
import com.myproject.TodoList.authentication.userDTO.request.UserSignInRequestDTO;
import com.myproject.TodoList.authentication.userDTO.request.UserSignUpRequestDTO;
import com.myproject.TodoList.authentication.userDTO.response.UserSignInResponseDTO;
import com.myproject.TodoList.authentication.userDTO.response.UserSignUpResponseDTO;
import com.myproject.TodoList.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;
    @Override
    public ResponseEntity<?> signup(UserSignUpRequestDTO userSignUpRequestDTO) {
        try{
            String username = userSignUpRequestDTO.getUsername();
            String password = userSignUpRequestDTO.getPassword();
            User user = userRepository.findByUsername(username);
            if (user != null){
                throw new BadRequestException("Username already exist");
            }
            User newUser = new User(username, passwordEncoder.encode(password));
            userRepository.save(newUser);
            String token = jwtTokenGenerator.createToken(newUser.getUsername(), newUser.getRoleAsList());
            return new ResponseEntity<>(new UserSignUpResponseDTO(username, token), HttpStatus.OK);
        }catch(AuthenticationException exception){
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }
    @Override
    public ResponseEntity<?> signin(UserSignInRequestDTO userSignInRequestDTO) {
        try {
            String username = userSignInRequestDTO.getUsername();
            String password = userSignInRequestDTO.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenGenerator.createToken(username, this.userRepository.findByUsername(username).getRoleAsList());
            return new ResponseEntity<>(new UserSignInResponseDTO(username, token), HttpStatus.OK);
        }catch (AuthenticationException exception){
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }
}
