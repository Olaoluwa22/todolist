package com.myproject.TodoList.authentication.repository;

import com.myproject.TodoList.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
