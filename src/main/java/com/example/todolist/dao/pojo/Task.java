package com.example.todolist.dao.pojo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;   // MongoDB auto-generated ID
    private String taskId;
    private String ownerId;
    private Long dueDate;
    private Integer priority;
    private LocalDateTime createdTs;
}
