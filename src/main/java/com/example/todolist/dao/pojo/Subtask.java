package com.example.todolist.dao.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subtask {
    private String subTaskId;
    private String taskId;
    private Status status;

    public boolean isCompleted() {
        return status != null && status == Status.COMPLETED;
    }
}
