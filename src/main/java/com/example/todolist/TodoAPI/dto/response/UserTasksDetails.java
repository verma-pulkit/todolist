package com.example.todolist.TodoAPI.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserTasksDetails {
    private List<TaskDetailsDto> taskDetailsList;
}
