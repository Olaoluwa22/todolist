package com.myproject.TodoList.authentication.userDTO.response;

public class UserSignUpResponseDTO {
    private String username;
    private String token;

    protected UserSignUpResponseDTO(){}

    public UserSignUpResponseDTO(String username, String token){
        super();
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
