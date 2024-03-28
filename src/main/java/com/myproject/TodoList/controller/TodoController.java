package com.myproject.TodoList.controller;

import com.myproject.TodoList.model.Todo;
import com.myproject.TodoList.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @GetMapping("/getAllTodoList")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    @PostMapping("/saveATodo")
    public Todo saveATodo(@Valid @RequestBody Todo todo){
        return todoRepository.save(todo);
    }
    @PutMapping("/updateATodo/{id}")
    public Todo updateATodo(@Valid @PathVariable long id, @RequestBody Todo todo){
        return todoRepository.save(todo);
    }
    @DeleteMapping("/deleteATodo/{id}")
    public void deleteATodo(@PathVariable long id){
        todoRepository.deleteById(id);
    }
}