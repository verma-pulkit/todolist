package com.example.todolist.dao.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Task {
    private String taskId;
    private String ownerId;
    private Long dueDate;
    private Integer priority;
    private LocalDateTime createdTs;
}
