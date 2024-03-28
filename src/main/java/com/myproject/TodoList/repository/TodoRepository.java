package com.myproject.TodoList.repository;

import com.myproject.TodoList.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUsername(String username);
    List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted);
    List<Todo> findByUsernameAndId(String username, long id);
    Long countByUsername(String username);
    Long countByUsernameAndIsCompleted(String username, boolean isCompleted);

}
