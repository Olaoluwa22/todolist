package com.myproject.TodoList.DTO.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class UpdateTodoRequestDTO {
    @NotEmpty
    private String title;
    @NotNull
    private Date targetDate;
    protected UpdateTodoRequestDTO(){}
    public UpdateTodoRequestDTO(String title, Date targetDate){
        super();
        this.title = title;
        this.targetDate = targetDate;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getTargetDate() {
        return targetDate;
    }
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
}
