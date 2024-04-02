package com.myproject.TodoList.authentication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name= "TodoListUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String username;
    @NotEmpty
    private String password;
    private String role;

    protected User() {
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
        this.role = "User";
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public List<String> getRoleAsList(){
        return Arrays.asList(this.role);
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
