package com.example.todolist.TodoAPI.dto.requests;

import lombok.Data;


@Data
public class TaskCreateRequest {
    private String taskId;
    private String ownerId;
    private String assigneeId;
    private long dueDate;
    private Integer priority;
}
