package com.myproject.TodoList.authentication.userDTO.request;

public class UserSignInRequestDTO {
    private String username;
    private String password;
    protected UserSignInRequestDTO(){
    }
    public UserSignInRequestDTO(String username, String password){
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
