package com.myproject.TodoList.controller;

import com.myproject.TodoList.DTO.CountResponse;
import com.myproject.TodoList.DTO.request.CreateTodoRequestDTO;
import com.myproject.TodoList.DTO.request.UpdateTodoRequestDTO;
import com.myproject.TodoList.model.Todo;
import com.myproject.TodoList.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @PostMapping("/create")
    public ResponseEntity<Todo> createATodo(@Valid @RequestBody CreateTodoRequestDTO requestDTO, Principal principal){
        return new ResponseEntity<>(todoService.create(requestDTO, principal.getName()), HttpStatus.CREATED);
    }
    @GetMapping("/readAllTodo")
    public ResponseEntity<List<Todo>> readAll(Principal principal, @RequestParam(required = false) String isCompleted){
        if(isCompleted != null) {
            return new ResponseEntity<>(todoService.readAllByIsCompleted(principal.getName(), isCompleted), HttpStatus.OK);
        }
        return new ResponseEntity<>(todoService.readAll(principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/countAllTodo")
    public ResponseEntity<CountResponse> countAll(Principal principal, @RequestParam(required = false) String isCompleted){
        if(isCompleted != null) {
            return new ResponseEntity<>(todoService.countAllByIsCompleted(principal.getName(), isCompleted), HttpStatus.OK);
        }
        return new ResponseEntity<>(todoService.countAll(principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/readAllTodo/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Todo>> readAllPageable(Principal principal, @PathVariable String pageNumber, @PathVariable String pageSize, @RequestParam(required = false) String isCompleted){
        if(isCompleted != null) {
            return new ResponseEntity<>(todoService.readAllByIsCompletedPageable(principal.getName(), isCompleted, pageNumber, pageSize), HttpStatus.OK);
        }
        return new ResponseEntity<>(todoService.readAllPageable(principal.getName(), pageNumber, pageSize), HttpStatus.OK);
    }
    @GetMapping("/read/{id}")
    public ResponseEntity<Todo> read(@PathVariable long id, Principal principal) {
        return new ResponseEntity<>(todoService.readById(id, principal.getName()), HttpStatus.OK);
    }
    @PutMapping(value = "/update/{id}/markcomplete")
    public ResponseEntity<Todo> updateTheIsCompleted(@PathVariable long id, Principal principal) {
        return new ResponseEntity<>(todoService.markCompleteById(id, principal.getName()), HttpStatus.OK);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long id, @Valid @RequestBody UpdateTodoRequestDTO updateTodoRequestDTO, Principal principal) {
        return new ResponseEntity<>(todoService.updateById(id, updateTodoRequestDTO, principal.getName()), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id, Principal principal) {
        todoService.deleteById(id, principal.getName());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}