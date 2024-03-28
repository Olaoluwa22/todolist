package com.myproject.TodoList.authentication.userDTO.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserSignUpRequestDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 8)
    private String password;
    protected UserSignUpRequestDTO(){
    }
    public UserSignUpRequestDTO(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
