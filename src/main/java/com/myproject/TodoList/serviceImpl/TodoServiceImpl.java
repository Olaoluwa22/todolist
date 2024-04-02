package com.myproject.TodoList.serviceImpl;

import com.myproject.TodoList.DTO.CountResponse;
import com.myproject.TodoList.DTO.request.CreateTodoRequestDTO;
import com.myproject.TodoList.DTO.request.UpdateTodoRequestDTO;
import com.myproject.TodoList.exception.BadRequestException;
import com.myproject.TodoList.exception.InvalidPageException;
import com.myproject.TodoList.exception.ResourceNotFoundException;
import com.myproject.TodoList.model.Todo;
import com.myproject.TodoList.repository.TodoPagingRepository;
import com.myproject.TodoList.repository.TodoRepository;
import com.myproject.TodoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoPagingRepository todoPagingRepository;

    @Override
    public Todo create(CreateTodoRequestDTO requestDTO, String username) {
        Todo todo = new Todo(requestDTO.getTitle(), username,requestDTO.getTargetDate());
        return todoRepository.save(todo);
    }
    @Override
    public CountResponse countAllByIsCompleted(String username, String isCompleted) {
        boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
        return new CountResponse(todoRepository.countByUsernameAndIsCompleted(username, _isCompleted));
    }
    @Override
    public CountResponse countAll(String username) {
        return new CountResponse(todoRepository.countByUsername(username));
    }
    @Override
    public List<Todo> readAllByIsCompleted(String username, String isCompleted) {
        return todoRepository.findAllByUsernameAndIsCompleted(username, isCompletedStringToBoolean(isCompleted));
    }
    @Override
    public List<Todo> readAll(String username) {
        return todoRepository.findAllByUsername(username);
    }
    @Override
    public List<Todo> readAllByIsCompletedPageable(String username, String isCompleted, String pageNumber, String pageSize) {
        boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
        int _pageNumber = pageNumberStringToInteger(pageNumber);
        int _pageSize = pageSizeStringToInteger(pageSize);

        Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
        return todoPagingRepository.findAllByUsernameAndIsCompleted(username, _isCompleted, pageable);
    }
    @Override
    public List<Todo> readAllPageable(String username, String pageNumber, String pageSize) {
        int _pageNumber = pageNumberStringToInteger(pageNumber);
        int _pageSize = pageSizeStringToInteger(pageSize);

        Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
        return todoPagingRepository.findAllByUsername(username, pageable);
    }
    @Override
    public Todo readById(long id, String username) {
        Todo todo = todoRepository.findByUsernameAndId(username, id);
        if (todo == null){
            throw new ResourceNotFoundException("Not found");
        }
        return todo;
    }
    @Override
    public Todo markCompleteById(long id, String username) {
        Todo todo = todoRepository.findByUsernameAndId(username, id);
        if(todo == null) {
            throw new ResourceNotFoundException("Todo not found");
        }
        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }
    @Override
    public Todo updateById(long id, UpdateTodoRequestDTO updateTodoRequestDTO, String username) {
        Todo todo = todoRepository.findByUsernameAndId(username, id);
        if (todo == null){
            throw new ResourceNotFoundException("Todo not found");
        }
        todo.setTitle(updateTodoRequestDTO.getTitle());
        todo.setTargetDate(updateTodoRequestDTO.getTargetDate());
        return todoRepository.save(todo);
    }
    @Override
    public void deleteById(long id, String username) {
        Todo todo = todoRepository.findByUsernameAndId(username, id);
        if (todo == null){
            throw new BadRequestException("Invalid input");
        }
        todoRepository.deleteById(id);
    }
    private boolean isCompletedStringToBoolean(String isCompleted) {
        try{
            return Boolean.parseBoolean(isCompleted);
        }catch(Exception exception){
            throw new BadRequestException("Invalid isCompleted value");
        }
    }
    private int pageNumberStringToInteger(String pageNumber) {
        int _pageNumber;

        try {
            _pageNumber = Integer.parseInt(pageNumber);
        } catch(Exception e) {
            throw new InvalidPageException("Invalid Page Number");
        }
        if(_pageNumber < 0) {
            throw new InvalidPageException("Invalid page number");
        }
        return _pageNumber;
    }
    private int pageSizeStringToInteger(String pageSize) {
        int _pageSize;
        try {
            _pageSize = Integer.parseInt(pageSize);
        } catch(Exception e) {
            throw new InvalidPageException("Invalid Page Size");
        }
        if(_pageSize < 1) {
            throw new InvalidPageException("Invalid page size");
        }
        return _pageSize;
    }
}
