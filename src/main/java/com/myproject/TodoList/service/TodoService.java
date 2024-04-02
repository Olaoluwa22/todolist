package com.myproject.TodoList.service;

import com.myproject.TodoList.DTO.CountResponse;
import com.myproject.TodoList.DTO.request.CreateTodoRequestDTO;
import com.myproject.TodoList.DTO.request.UpdateTodoRequestDTO;
import com.myproject.TodoList.model.Todo;

import java.util.List;

public interface TodoService {
    Todo create(CreateTodoRequestDTO requestDTO, String username);
    CountResponse countAllByIsCompleted(String username, String isCompleted);
    CountResponse countAll(String name);
    List<Todo> readAllByIsCompleted(String username, String isCompleted);
    List<Todo> readAll(String username);
    List<Todo> readAllByIsCompletedPageable(String username, String isCompleted, String pageNumber, String pageSize);
    List<Todo> readAllPageable(String username, String pageNumber, String pageSize);
    Todo readById(long id, String username);
    Todo markCompleteById(long id, String username);
    Todo updateById(long id, UpdateTodoRequestDTO updateTodoRequestDTO, String username);
    void deleteById(long id, String username);
}
