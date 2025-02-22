package com.example.todolist.dao.pojo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "subtasks")
public class Subtask {
    @Id
    private String id;   // MongoDB auto-generated ID
    private String subTaskId;
    private String taskId;
    private Status status;

    public boolean isCompleted() {
        return status != null && status == Status.COMPLETED;
    }
}
