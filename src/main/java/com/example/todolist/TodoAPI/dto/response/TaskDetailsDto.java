package com.example.todolist.TodoAPI.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaskDetailsDto {
    private String taskId;
    private Long dueDate;
    private Integer priority;
    private double progressPercentage;
}
