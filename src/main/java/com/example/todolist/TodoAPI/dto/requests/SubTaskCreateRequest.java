package com.example.todolist.TodoAPI.dto.requests;

import com.example.todolist.dao.pojo.Status;
import lombok.Data;

@Data
public class SubTaskCreateRequest {
    private String subTaskId;
    private String taskId;
    private Status status;
}
