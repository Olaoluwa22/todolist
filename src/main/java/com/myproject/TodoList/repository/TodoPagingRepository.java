package com.myproject.TodoList.repository;

import com.myproject.TodoList.model.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TodoPagingRepository extends PagingAndSortingRepository<Todo, Long> {
    List<Todo> findAllByUsername(String username, Pageable pageable);
    List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, Pageable pageable);
}
